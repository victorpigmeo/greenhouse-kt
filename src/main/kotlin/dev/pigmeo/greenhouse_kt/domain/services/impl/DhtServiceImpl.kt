package dev.pigmeo.greenhouse_kt.domain.services.impl

import dev.pigmeo.greenhouse_kt.application.http_client.EspClient
import dev.pigmeo.greenhouse_kt.application.mappers.DhtMapper
import dev.pigmeo.greenhouse_kt.domain.entities.DhtRead
import dev.pigmeo.greenhouse_kt.domain.services.DhtService
import org.springframework.stereotype.Service

@Service
class DhtServiceImpl(
    private val espClient: EspClient,
    private val dhtMapper: DhtMapper
): DhtService {

    override fun getLiveDhtRead(): DhtRead {
        return dhtMapper.mapToDomain(espClient.getDhtRead())
    }
}