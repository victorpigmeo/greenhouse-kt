package dev.pigmeo.greenhouse_kt.application.mappers

import dev.pigmeo.greenhouse_kt.application.payloads.GpioDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioDtoOut
import dev.pigmeo.greenhouse_kt.domain.entities.Gpio
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Component
@Mapper(config = MapperConfig::class)
interface GpioMapper {

    fun mapToDomain(gpioDtoIn: GpioDtoIn): Gpio;
    fun mapToOut(gpio: Gpio): GpioDtoOut;

}