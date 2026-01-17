package dev.pigmeo.greenhousekt.infrastructure.schedulers

import dev.pigmeo.greenhousekt.application.httpclient.EspClient
import dev.pigmeo.greenhousekt.application.mappers.DhtMapper
import dev.pigmeo.greenhousekt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhousekt.infrastructure.repository.DhtRepository
import net.javacrumbs.shedlock.core.LockAssert
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DhtScheduler(
    private val espClient: EspClient,
    private val dhtMapper: DhtMapper,
    private val dhtRepository: DhtRepository
) {

    @Transactional
    @Scheduled(initialDelay = 0, fixedDelay = 60000)
    @SchedulerLock(name = "dhtSchedulerLock", lockAtLeastFor = "30s")
    fun getLiveDhtRead() {
        LockAssert.assertLocked()

        val dhtRead: DhtReadEspDtoIn = this.espClient.getDhtRead()

        this.dhtRepository.save(this.dhtMapper.mapToDomain(dhtRead))
    }
}
