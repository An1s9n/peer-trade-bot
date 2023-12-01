package ru.an1s9n.peertradebot.binance

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import ru.an1s9n.peertradebot.Asset
import ru.an1s9n.peertradebot.Bank
import ru.an1s9n.peertradebot.Fiat
import ru.an1s9n.peertradebot.TradeType
import java.math.BigDecimal

@ApplicationScoped
@RegisterRestClient(configKey = "binance")
interface BinanceP2PClient {

  @POST
  @Path("/bapi/c2c/v2/friendly/c2c/adv/search")
  fun search(request: SearchRequest): SearchResponse
}

// request

data class SearchRequest(
  val fiat: Fiat,
  val page: Int,
  val rows: Int,
  val tradeType: TradeType,
  val asset: Asset,
  val payTypes: List<Bank>,
  val publisherType: String?,
)

// response

data class SearchResponse(val data: List<SearchResponseData>)

data class SearchResponseData(val adv: Advertisement)

data class Advertisement(
  val advNo: String,
  val price: BigDecimal,
  val minSingleTransAmount: BigDecimal,
  val dynamicMaxSingleTransAmount: BigDecimal,
)
