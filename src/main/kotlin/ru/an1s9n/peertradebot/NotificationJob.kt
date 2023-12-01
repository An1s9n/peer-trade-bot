package ru.an1s9n.peertradebot

import io.quarkus.scheduler.Scheduled
import io.quarkus.scheduler.Scheduled.ConcurrentExecution.SKIP
import jakarta.enterprise.context.ApplicationScoped
import mu.KotlinLogging.logger
import kotlin.time.DurationUnit.MILLISECONDS
import kotlin.time.TimeSource.Monotonic.markNow

@ApplicationScoped
class NotificationJob(private val notificationService: NotificationService) {

  private val log = logger {}

  @Scheduled(identity = "notify-health-job", cron = "\${notification.health-cron}", concurrentExecution = SKIP)
  fun notifyHealth() {
    log.debug { "notifyHealth triggered" }
    val startedAt = markNow()
    notificationService.notifyHealth()
    log.debug { "notifyHealth completed in ${startedAt.elapsedNow().toString(MILLISECONDS)}" }
  }

  @Scheduled(identity = "notify-changes-job", cron = "\${notification.changes-cron}", concurrentExecution = SKIP)
  fun notifyChanges() {
    log.debug { "notifyChanges triggered" }
    val startedAt = markNow()
    notificationService.notifyChanges()
    log.debug { "notifyChanges completed in ${startedAt.elapsedNow().toString(MILLISECONDS)}" }
  }
}
