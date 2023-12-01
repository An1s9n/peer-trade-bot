package ru.an1s9n.peertradebot.telegram

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramException
import com.pengrad.telegrambot.request.BaseRequest
import com.pengrad.telegrambot.response.BaseResponse

fun <T : BaseRequest<T, R>, R : BaseResponse> TelegramBot.executeOrThrow(request: BaseRequest<T, R>): R {
  val response = execute(request)
  if (!response.isOk) {
    throw TelegramException("TelegramBot.execute failed <${response.errorCode()}>: ${response.description()}", response)
  }
  return response
}
