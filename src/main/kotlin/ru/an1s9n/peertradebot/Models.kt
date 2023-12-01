package ru.an1s9n.peertradebot

import com.fasterxml.jackson.annotation.JsonValue

enum class Fiat(val symbol: String) {
  USD("$"),
  TRY("₺"),
}

enum class TradeType {
  BUY,
  SELL,
}

enum class Asset(val symbol: String) {
  USDT("USD₮"),
}

enum class Bank(@JsonValue val identifier: String, val emoji: String) {
  TBC_BANK("TBCbank", "\uD83D\uDD35"),
  BANK_OF_GEORGIA("BankofGeorgia", "\uD83D\uDFE0"),
  ZIRAAT("Ziraat", "\uD83D\uDD34"),
}
