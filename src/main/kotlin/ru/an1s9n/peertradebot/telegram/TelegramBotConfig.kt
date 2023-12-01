package ru.an1s9n.peertradebot.telegram

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.response.SendResponse
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.net.URL
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@RegisterForReflection(targets = [SendResponse::class, URL::class], registerFullHierarchy = true)
@Suppress("unused")
class TelegramBotConfig(
  @ConfigProperty(name = "telegram-bot.base-url-override") private val baseUrlOverride: Optional<URL>,
  @ConfigProperty(name = "telegram-bot.token") private val token: String,
) {

  @ApplicationScoped
  fun produceTelegramBot(): TelegramBot = TelegramBot.Builder(token)
    .apiUrl(baseUrlOverride.getOrNull()?.toString())
    .build()
}
