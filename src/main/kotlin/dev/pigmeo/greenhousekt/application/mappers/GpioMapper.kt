package dev.pigmeo.greenhousekt.application.mappers

import dev.pigmeo.greenhousekt.application.payloads.GpioDtoIn
import dev.pigmeo.greenhousekt.application.payloads.GpioDtoOut
import dev.pigmeo.greenhousekt.application.payloads.GpioEspDtoOut
import dev.pigmeo.greenhousekt.domain.entities.Gpio
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.stereotype.Component

@Component
@Mapper(config = MapperConfig::class)
interface GpioMapper {

    fun mapToDomain(gpioDtoIn: GpioDtoIn): Gpio
    @Mapping(target = "stateValue", source = "state.value")
    @Mapping(target = "actionValue", source = "type.value")
    fun mapToEspOut(gpio: Gpio): GpioEspDtoOut
    fun mapToOut(gpio: Gpio): GpioDtoOut
    fun mapToListOut(gpioList: List<Gpio>): List<GpioDtoOut>

}
