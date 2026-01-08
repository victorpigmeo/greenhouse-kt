package dev.pigmeo.greenhouse_kt.config

import dev.pigmeo.greenhouse_kt.infrastructure.errorhandler.SchedulerErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
@EnableScheduling
class SchedulerConfig(
    private val customSchedulerErrorHandler: SchedulerErrorHandler
) {
    @Bean
    fun taskScheduler(): ThreadPoolTaskScheduler {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()

        threadPoolTaskScheduler.setErrorHandler(SchedulerErrorHandler())
        threadPoolTaskScheduler.initialize()

        return threadPoolTaskScheduler
    }
}