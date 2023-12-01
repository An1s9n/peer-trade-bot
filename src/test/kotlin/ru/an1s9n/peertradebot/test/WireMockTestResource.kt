package ru.an1s9n.peertradebot.test

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager

class WireMockTestResource : QuarkusTestResourceLifecycleManager {

  private lateinit var binanceMockServer: WireMockServer

  private lateinit var tgBotMockServer: WireMockServer

  override fun start(): Map<String, String> {
    binanceMockServer = WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort()).apply { start() }
    tgBotMockServer = WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort()).apply { start() }
    return mapOf(
      "binance-mock-server.base-url" to binanceMockServer.baseUrl(),
      "telegram-bot-mock-server.base-url" to tgBotMockServer.baseUrl(),
    )
  }

  override fun stop() {
    binanceMockServer.stop()
    tgBotMockServer.stop()
  }

  override fun inject(testInjector: QuarkusTestResourceLifecycleManager.TestInjector) {
    testInjector.injectIntoFields(binanceMockServer) {
      it.type == WireMockServer::class.java && it.name == "binanceMockServer"
    }
    testInjector.injectIntoFields(tgBotMockServer) {
      it.type == WireMockServer::class.java && it.name == "tgBotMockServer"
    }
  }
}
