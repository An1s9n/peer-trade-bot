package ru.an1s9n.peertradebot

import java.math.BigDecimal

interface PriceRepository {

  fun getCurrentPrice(tradeId: String): BigDecimal?

  fun saveNewPrice(tradeId: String, newPrice: BigDecimal?)
}
