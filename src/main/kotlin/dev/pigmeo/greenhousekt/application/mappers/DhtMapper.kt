package dev.pigmeo.greenhousekt.application.mappers

import dev.pigmeo.greenhousekt.application.payloads.DhtReadDtoOut
import dev.pigmeo.greenhousekt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhousekt.domain.entities.DhtRead
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Component
@Mapper(config = MapperConfig::class)
interface DhtMapper {
    fun mapToOut(dhtRead: DhtRead): DhtReadDtoOut
    fun mapToDomain(dhtReadEspDtoIn: DhtReadEspDtoIn): DhtRead
}
