package ru.an1s9n.peertradebot.test

import org.awaitility.core.ConditionFactory
import kotlin.time.Duration
import kotlin.time.toJavaDuration

infix fun ConditionFactory.atMost(duration: Duration): ConditionFactory = atMost(duration.toJavaDuration())

infix fun ConditionFactory.withPollDelay(duration: Duration): ConditionFactory = pollDelay(duration.toJavaDuration())
