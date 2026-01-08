package dev.pigmeo.greenhouse_kt.application.mappers

import dev.pigmeo.greenhouse_kt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.DhtReadDtoOut
import dev.pigmeo.greenhouse_kt.domain.entities.DhtRead
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.stereotype.Component

@Component
@Mapper(config = MapperConfig::class)
interface DhtMapper {
    fun mapToOut(dhtRead: DhtRead): DhtReadDtoOut
    @Mapping(target = "heatIndex", source = "heat_index")
    fun mapToDomain(dhtReadEspDtoIn: DhtReadEspDtoIn): DhtRead
}