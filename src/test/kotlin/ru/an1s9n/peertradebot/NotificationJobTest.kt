package ru.an1s9n.peertradebot

import com.github.tomakehurst.wiremock.WireMockServer
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE
import jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals
import org.awaitility.kotlin.await
import org.awaitility.kotlin.untilAsserted
import ru.an1s9n.peertradebot.binance.searchPage1EmptyStub
import ru.an1s9n.peertradebot.binance.searchPage1NonSuitablePriceStub
import ru.an1s9n.peertradebot.binance.searchPage1SuitablePriceStub
import ru.an1s9n.peertradebot.binance.searchPage2SuitablePriceStub
import ru.an1s9n.peertradebot.telegram.sendMessageOkStub
import ru.an1s9n.peertradebot.test.WireMockTestResource
import ru.an1s9n.peertradebot.test.atMost
import ru.an1s9n.peertradebot.test.withPollDelay
import java.math.BigDecimal
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@QuarkusTest
@QuarkusTestResource(WireMockTestResource::class)
class NotificationJobTest {

  private lateinit var binanceMockServer: WireMockServer

  private lateinit var tgBotMockServer: WireMockServer

  @Inject
  private lateinit var priceRepo: PriceRepository

  @BeforeTest
  fun resetMockServers() {
    binanceMockServer.resetAll()
    tgBotMockServer.resetAll()
  }

  @Test
  fun `notifyHealth test`() {
    tgBotMockServer.sendMessageOkStub()

    await atMost 5.seconds untilAsserted {
      val tgBotRequestBodies = getTgBotRequestBodies()
      assertTrue(tgBotRequestBodies.isNotEmpty())
      assertEquals(tgBotHealthcheck, tgBotRequestBodies[0])
    }
  }

  @Test
  fun `notifyChanges test when no price was saved and non-suitable price reached don't expect notification`() {
    priceRepo.saveNewPrice("trade-id-test-1", null)
    binanceMockServer.searchPage1NonSuitablePriceStub()

    await withPollDelay 2500.milliseconds atMost 5.seconds untilAsserted {
      assertEquals(BigDecimal.valueOf(1016L, 3), priceRepo.getCurrentPrice("trade-id-test-1"))
      val distinctBinanceRequestBodies = getDistinctBinanceRequestBodies()
      assertEquals(1, distinctBinanceRequestBodies.size)
      assertJsonEquals(BINANCE_SEARCH_PAGE_1, distinctBinanceRequestBodies[0])
      val tgBotRequestBodies = getTgBotRequestBodies { !it.contains("healthcheck") }
      assertTrue(tgBotRequestBodies.isEmpty())
    }
  }

  @Test
  fun `notifyChanges test when no price was saved and suitable price reached expect notification`() {
    priceRepo.saveNewPrice("trade-id-test-1", null)
    tgBotMockServer.sendMessageOkStub()
    binanceMockServer.searchPage2SuitablePriceStub()
    binanceMockServer.searchPage1SuitablePriceStub()

    await withPollDelay 2500.milliseconds atMost 5.seconds untilAsserted {
      assertEquals(BigDecimal.valueOf(1014L, 3), priceRepo.getCurrentPrice("trade-id-test-1"))
      val distinctBinanceRequestBodies = getDistinctBinanceRequestBodies()
      assertEquals(2, distinctBinanceRequestBodies.size)
      assertJsonEquals(BINANCE_SEARCH_PAGE_1, distinctBinanceRequestBodies[0])
      assertJsonEquals(BINANCE_SEARCH_PAGE_2, distinctBinanceRequestBodies[1])
      val tgBotRequestBodies = getTgBotRequestBodies { !it.contains("healthcheck") }
      assertEquals(1, tgBotRequestBodies.size)
      assertEquals(tgBotBestAdv, tgBotRequestBodies[0])
    }
  }

  @Test
  fun `notifyChanges test when non-suitable price was saved and advs are over don't expect notification`() {
    priceRepo.saveNewPrice("trade-id-test-1", BigDecimal.valueOf(1016L, 3))
    binanceMockServer.searchPage1EmptyStub()

    await withPollDelay 2500.milliseconds atMost 5.seconds untilAsserted {
      assertNull(priceRepo.getCurrentPrice("trade-id-test-1"))
      val distinctBinanceRequestBodies = getDistinctBinanceRequestBodies()
      assertEquals(1, distinctBinanceRequestBodies.size)
      assertJsonEquals(BINANCE_SEARCH_PAGE_1, distinctBinanceRequestBodies[0])
      val tgBotRequestBodies = getTgBotRequestBodies { !it.contains("healthcheck") }
      assertTrue(tgBotRequestBodies.isEmpty())
    }
  }

  @Test
  fun `notifyChanges test when suitable price was saved and advs are over expect notification`() {
    priceRepo.saveNewPrice("trade-id-test-1", BigDecimal.valueOf(1014L, 3))
    tgBotMockServer.sendMessageOkStub()
    binanceMockServer.searchPage1EmptyStub()

    await withPollDelay 2500.milliseconds atMost 5.seconds untilAsserted {
      assertNull(priceRepo.getCurrentPrice("trade-id-test-1"))
      val distinctBinanceRequestBodies = getDistinctBinanceRequestBodies()
      assertEquals(1, distinctBinanceRequestBodies.size)
      assertJsonEquals(BINANCE_SEARCH_PAGE_1, distinctBinanceRequestBodies[0])
      val tgBotRequestBodies = getTgBotRequestBodies { !it.contains("healthcheck") }
      assertEquals(1, tgBotRequestBodies.size)
      assertEquals(tgBotNoMoreAdvs, tgBotRequestBodies[0])
    }
  }

  @Test
  fun `notifyChanges test when price not changed don't expect notification`() {
    priceRepo.saveNewPrice("trade-id-test-1", BigDecimal.valueOf(1014L, 3))
    binanceMockServer.searchPage2SuitablePriceStub()
    binanceMockServer.searchPage1SuitablePriceStub()

    await withPollDelay 2500.milliseconds atMost 5.seconds untilAsserted {
      assertEquals(BigDecimal.valueOf(1014L, 3), priceRepo.getCurrentPrice("trade-id-test-1"))
      val distinctBinanceRequestBodies = getDistinctBinanceRequestBodies()
      assertEquals(2, distinctBinanceRequestBodies.size)
      assertJsonEquals(BINANCE_SEARCH_PAGE_1, distinctBinanceRequestBodies[0])
      assertJsonEquals(BINANCE_SEARCH_PAGE_2, distinctBinanceRequestBodies[1])
      val tgBotRequestBodies = getTgBotRequestBodies { !it.contains("healthcheck") }
      assertTrue(tgBotRequestBodies.isEmpty())
    }
  }

  @Test
  fun `notifyChanges test when suitable price was saved and non-suitable price reached expect notification`() {
    priceRepo.saveNewPrice("trade-id-test-1", BigDecimal.valueOf(1014L, 3))
    tgBotMockServer.sendMessageOkStub()
    binanceMockServer.searchPage1NonSuitablePriceStub()

    await withPollDelay 2500.milliseconds atMost 5.seconds untilAsserted {
      assertEquals(BigDecimal.valueOf(1016L, 3), priceRepo.getCurrentPrice("trade-id-test-1"))
      val distinctBinanceRequestBodies = getDistinctBinanceRequestBodies()
      assertEquals(1, distinctBinanceRequestBodies.size)
      assertJsonEquals(BINANCE_SEARCH_PAGE_1, distinctBinanceRequestBodies[0])
      val tgBotRequestBodies = getTgBotRequestBodies { !it.contains("healthcheck") }
      assertEquals(1, tgBotRequestBodies.size)
      assertEquals(tgBotNoMoreAdvs, tgBotRequestBodies[0])
    }
  }

  @Test
  fun `notifyChanges test when non-suitable price was saved and suitable price reached expect notification`() {
    priceRepo.saveNewPrice("trade-id-test-1", BigDecimal.valueOf(1016L, 3))
    tgBotMockServer.sendMessageOkStub()
    binanceMockServer.searchPage2SuitablePriceStub()
    binanceMockServer.searchPage1SuitablePriceStub()

    await withPollDelay 2500.milliseconds atMost 5.seconds untilAsserted {
      assertEquals(BigDecimal.valueOf(1014L, 3), priceRepo.getCurrentPrice("trade-id-test-1"))
      val distinctBinanceRequestBodies = getDistinctBinanceRequestBodies()
      assertEquals(2, distinctBinanceRequestBodies.size)
      assertJsonEquals(BINANCE_SEARCH_PAGE_1, distinctBinanceRequestBodies[0])
      assertJsonEquals(BINANCE_SEARCH_PAGE_2, distinctBinanceRequestBodies[1])
      val tgBotRequestBodies = getTgBotRequestBodies { !it.contains("healthcheck") }
      assertEquals(1, tgBotRequestBodies.size)
      assertEquals(tgBotBestAdv, tgBotRequestBodies[0])
    }
  }

  private fun getDistinctBinanceRequestBodies(): List<String> {
    val binanceEvents = binanceMockServer.allServeEvents.reversed().distinctBy { it.request.bodyAsBase64 }
    assertTrue(binanceEvents.all { it.request.header(CONTENT_TYPE).firstValue() == APPLICATION_JSON })
    return binanceEvents.map { it.request.bodyAsString }
  }

  private fun getTgBotRequestBodies(bodyFilter: (String) -> Boolean = { true }): List<String> {
    val tgBotEvents = tgBotMockServer.allServeEvents.reversed().filter { bodyFilter(it.request.bodyAsString) }
    assertTrue(tgBotEvents.all { it.request.header(CONTENT_TYPE).firstValue() == APPLICATION_FORM_URLENCODED })
    return tgBotEvents.map { it.request.bodyAsString }
  }

  private val tgBotHealthcheck: String = formUrlEncodedBody("chat_id" to 123, "text" to "healthcheck")

  private val tgBotBestAdv: String = formUrlEncodedBody(
    "chat_id" to 123,
    "text" to "best adv \uD83D\uDFE0: 1USD₮ = 1.014\$ (150..18 109\$)",
    "message_thread_id" to 1,
  )

  private val tgBotNoMoreAdvs: String = formUrlEncodedBody(
    "chat_id" to 123,
    "text" to "no more advs satisfying threshold 1.015\$",
    "message_thread_id" to 1,
  )

  private fun formUrlEncodedBody(vararg params: Pair<String, Any>): String =
    params.joinToString("&") { (k, v) -> "$k=${URLEncoder.encode("$v", StandardCharsets.UTF_8).replace("+", "%20")}" }
}

// language=JSON
private const val BINANCE_SEARCH_PAGE_1: String = """
  {
    "fiat": "USD",
    "page": 1,
    "rows": 2,
    "tradeType": "BUY",
    "asset": "USDT",
    "payTypes": [ "BankofGeorgia" ],
    "publisherType": "merchant"
  }
"""

// language=JSON
private const val BINANCE_SEARCH_PAGE_2: String = """
  {
    "fiat": "USD",
    "page": 2,
    "rows": 2,
    "tradeType": "BUY",
    "asset": "USDT",
    "payTypes": [ "BankofGeorgia" ],
    "publisherType": "merchant"
  }
"""
