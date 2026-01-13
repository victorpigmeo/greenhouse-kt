package dev.pigmeo.greenhouse_kt

import dev.pigmeo.greenhouse_kt.application.payloads.DhtReadDtoOut
import dev.pigmeo.greenhouse_kt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioDtoOut
import dev.pigmeo.greenhouse_kt.application.payloads.GpioEspDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioEspDtoOut
import dev.pigmeo.greenhouse_kt.application.payloads.LoginRequest
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@RegisterReflectionForBinding(
	LoginRequest::class,
	GpioDtoOut::class,
	GpioDtoIn::class,
	GpioEspDtoOut::class,
	GpioEspDtoIn::class,
	DhtReadDtoOut::class,
	DhtReadEspDtoIn::class
)
class GreenhouseKtApplication

fun main(args: Array<String>) {
	runApplication<GreenhouseKtApplication>(*args)
}