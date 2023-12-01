package ru.an1s9n.peertradebot

import io.smallrye.config.ConfigMapping
import java.math.BigDecimal

@ConfigMapping(prefix = "notification.settings")
interface NotificationSettings {

  fun telegramChatId(): String

  fun trades(): List<Trade>

  interface Trade {

    fun id(): String

    fun telegramThreadId(): Int

    fun merchantOnly(): Boolean

    fun type(): TradeType

    fun fiat(): Fiat

    fun asset(): Asset

    fun thresholdInclusive(): BigDecimal

    fun bank(): Bank
  }
}
