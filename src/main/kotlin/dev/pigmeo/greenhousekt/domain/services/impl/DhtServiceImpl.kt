package dev.pigmeo.greenhousekt.domain.services.impl

import dev.pigmeo.greenhousekt.application.httpclient.EspClient
import dev.pigmeo.greenhousekt.application.mappers.DhtMapper
import dev.pigmeo.greenhousekt.domain.entities.DhtRead
import dev.pigmeo.greenhousekt.domain.services.DhtService
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
