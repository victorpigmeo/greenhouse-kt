package dev.pigmeo.greenhousekt.infrastructure.errorhandler

import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import org.springframework.stereotype.Component
import org.springframework.util.ErrorHandler
import org.springframework.web.reactive.function.client.WebClientRequestException

@Component
class SchedulerErrorHandler : ErrorHandler {
    private val logger = KotlinLogging.logger {}

    override fun handleError(t: Throwable) {
        when (t) {
            is WebClientRequestException -> {
                logger.error() { "Unable to reach ESP: ${t.message}" }
            }

            is CallNotPermittedException -> {
                logger.error() { "Circuit Breaker '${t.causingCircuitBreakerName}' is OPEN" }
            }

            else -> {
                logger.error(t) { "Unknown exception on scheduler: ${t.message}" }
            }
        }
    }
}
