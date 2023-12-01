package ru.an1s9n.peertradebot

import jakarta.enterprise.context.ApplicationScoped
import java.math.BigDecimal
import java.util.concurrent.ConcurrentHashMap

@ApplicationScoped
class InMemoryPriceRepository : PriceRepository {

  private val curPrices: MutableMap<String, BigDecimal?> = ConcurrentHashMap()

  override fun getCurrentPrice(tradeId: String): BigDecimal? = curPrices[tradeId]

  override fun saveNewPrice(tradeId: String, newPrice: BigDecimal?) {
    if (newPrice == null) curPrices.remove(tradeId) else curPrices[tradeId] = newPrice
  }
}
