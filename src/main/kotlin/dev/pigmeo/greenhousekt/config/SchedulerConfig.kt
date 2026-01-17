package dev.pigmeo.greenhousekt.config

import dev.pigmeo.greenhousekt.infrastructure.errorhandler.SchedulerErrorHandler
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "1m")
class SchedulerConfig{
    @Bean
    fun taskScheduler(): ThreadPoolTaskScheduler {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()

        threadPoolTaskScheduler.setErrorHandler(SchedulerErrorHandler())
        threadPoolTaskScheduler.initialize()

        return threadPoolTaskScheduler
    }
}
