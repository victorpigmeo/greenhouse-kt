package dev.pigmeo.greenhouse_kt.infrastructure.schedulers

import dev.pigmeo.greenhouse_kt.application.http_client.EspClient
import dev.pigmeo.greenhouse_kt.application.mappers.DhtMapper
import dev.pigmeo.greenhouse_kt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhouse_kt.infrastructure.repository.DhtRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class DhtScheduler(
    private val espClient: EspClient,
    private val dhtMapper: DhtMapper,
    private val dhtRepository: DhtRepository
) {

    @Scheduled(initialDelay = 0, fixedDelay = 60000)
    fun getLiveDhtRead() {
        val dhtRead: DhtReadEspDtoIn = this.espClient.getDhtRead()

        this.dhtRepository.save(this.dhtMapper.mapToDomain(dhtRead))
    }
}