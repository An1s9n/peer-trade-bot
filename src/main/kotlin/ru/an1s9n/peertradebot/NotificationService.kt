package ru.an1s9n.peertradebot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.request.SendMessage
import jakarta.enterprise.context.ApplicationScoped
import mu.KotlinLogging.logger
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import ru.an1s9n.peertradebot.NotificationSettings.Trade
import ru.an1s9n.peertradebot.TradeType.BUY
import ru.an1s9n.peertradebot.TradeType.SELL
import ru.an1s9n.peertradebot.binance.BinanceP2PClient
import ru.an1s9n.peertradebot.binance.SearchRequest
import ru.an1s9n.peertradebot.telegram.executeOrThrow
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

@ApplicationScoped
class NotificationService(
  private val notificationSettings: NotificationSettings,
  @RestClient private val binanceP2PClient: BinanceP2PClient,
  @ConfigProperty(name = "binance.advs-per-page") private val advsPerPage: Int,
  private val priceRepo: PriceRepository,
  private val tgBot: TelegramBot,
) {

  private val log = logger {}

  private val amountDF: DecimalFormat = DecimalFormat("#,###", DecimalFormatSymbols().apply { groupingSeparator = ' ' })

  fun notifyHealth() {
    try {
      tgBot.executeOrThrow(SendMessage(notificationSettings.telegramChatId(), "healthcheck"))
    } catch (e: Exception) {
      log.warn(e) { "notifyHealth failed" }
    }
  }

  fun notifyChanges() {
    for (trade in notificationSettings.trades()) {
      try {
        val curPrice = priceRepo.getCurrentPrice(trade.id())
        val bestAdvs = searchBestAdvs(trade)
        val bestAdv = bestAdvs.firstOrNull()
        if (curPrice != bestAdv?.price) {
          val tgNotification = prepareTgNotification(trade, curPrice, bestAdvs)
          if (tgNotification != null) {
            tgBot.executeOrThrow(tgNotification)
          }
          priceRepo.saveNewPrice(trade.id(), bestAdv?.price)
        }
      } catch (e: Exception) {
        log.warn(e) { "notifyChanges failed for trade '${trade.id()}'" }
      }
    }
  }

  private fun searchBestAdvs(trade: Trade): List<AdvDto> =
    buildList {
      var page = 1
      do {
        val advsPage = binanceP2PClient.search(
          SearchRequest(
            fiat = trade.fiat(),
            page = page++,
            rows = advsPerPage,
            tradeType = trade.type(),
            asset = trade.asset(),
            payTypes = listOf(trade.bank()),
            publisherType = if (trade.merchantOnly()) "merchant" else null
          )
        ).data.map {
          AdvDto(
            price = it.adv.price,
            minAmount = it.adv.minSingleTransAmount,
            maxAmount = it.adv.dynamicMaxSingleTransAmount,
          )
        }
        addAll(advsPage.dropLastWhile { it.price != (firstOrNull()?.price ?: advsPage.first().price) })
      } while (advsPage.isNotEmpty() && advsPage.last().price == first().price)
    }

  private fun prepareTgNotification(trade: Trade, curPrice: BigDecimal?, bestAdvs: List<AdvDto>): SendMessage? {
    val bestAdv = bestAdvs.firstOrNull()
    return when {
      shouldNotifyBestAdv(trade, bestAdv) -> prepareBestAdvNotification(trade, bestAdvs)
      shouldNotifyNoMoreAdvs(trade, curPrice, bestAdv) -> prepareNoMoreAdvsNotification(trade)
      else -> null
    }?.let {
      SendMessage(notificationSettings.telegramChatId(), it).messageThreadId(trade.telegramThreadId())
    }
  }

  private fun shouldNotifyBestAdv(trade: Trade, bestAdv: AdvDto?): Boolean {
    val thrInc = trade.thresholdInclusive()
    return bestAdv?.price != null && when (trade.type()) {
      BUY -> bestAdv.price <= thrInc
      SELL -> bestAdv.price >= thrInc
    }
  }

  private fun prepareBestAdvNotification(trade: Trade, bestAdvs: List<AdvDto>): String {
    val bank = trade.bank().emoji
    val assetSymbol = trade.asset().symbol
    val bestAdvPrice = bestAdvs.first().price.toPlainString()
    val fiatSymbol = trade.fiat().symbol
    val minAmount = amountDF.format(bestAdvs.minOf { it.minAmount })
    val maxAmount = amountDF.format(bestAdvs.sumOf { it.maxAmount })
    return "best adv $bank: 1$assetSymbol = $bestAdvPrice$fiatSymbol ($minAmount..$maxAmount$fiatSymbol)"
  }

  private fun shouldNotifyNoMoreAdvs(trade: Trade, curPrice: BigDecimal?, bestAdv: AdvDto?): Boolean {
    val thrInc = trade.thresholdInclusive()
    return curPrice != null && when (trade.type()) {
      BUY -> curPrice <= thrInc && (bestAdv?.price == null || bestAdv.price > thrInc)
      SELL -> curPrice >= thrInc && (bestAdv?.price == null || bestAdv.price < thrInc)
    }
  }

  private fun prepareNoMoreAdvsNotification(trade: Trade): String =
    "no more advs satisfying threshold ${trade.thresholdInclusive()}${trade.fiat().symbol}"

  private data class AdvDto(val price: BigDecimal, val minAmount: BigDecimal, val maxAmount: BigDecimal)
}
