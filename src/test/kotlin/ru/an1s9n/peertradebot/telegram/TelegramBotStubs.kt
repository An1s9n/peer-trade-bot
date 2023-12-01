package ru.an1s9n.peertradebot.telegram

import com.github.tomakehurst.wiremock.WireMockServer
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.post
import com.marcinziolo.kotlin.wiremock.returnsJson

fun WireMockServer.sendMessageOkStub() {
  post {
    urlPath equalTo "/bottg-bot-token-test/sendMessage"
  } returnsJson {
    body =
      // language=JSON
      """
        {
          "ok": true,
          "result": {
            "message_id": 352,
            "from": {
              "id": 6209050477,
              "is_bot": true,
              "first_name": "PeerTradeBot",
              "username": "PeerTradeBot"
            },
            "chat": {
              "id": 123,
              "title": "Peer trade group",
              "is_forum": true,
              "type": "supergroup"
            },
            "date": 1701265615,
            "message_thread_id": 1,
            "text": "foo",
            "is_topic_message": true
          }
        }
      """.trimIndent()
  }
}
