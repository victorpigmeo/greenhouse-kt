package dev.pigmeo.greenhousekt

import dev.pigmeo.greenhousekt.application.payloads.DhtReadDtoOut
import dev.pigmeo.greenhousekt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhousekt.application.payloads.GpioDtoIn
import dev.pigmeo.greenhousekt.application.payloads.GpioDtoOut
import dev.pigmeo.greenhousekt.application.payloads.GpioEspDtoIn
import dev.pigmeo.greenhousekt.application.payloads.GpioEspDtoOut
import dev.pigmeo.greenhousekt.application.payloads.LoginRequest
import dev.pigmeo.greenhousekt.application.payloads.UserDtoIn
import dev.pigmeo.greenhousekt.application.payloads.UserDtoOut
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
	DhtReadEspDtoIn::class,
	UserDtoIn::class,
	UserDtoOut::class
)
class GreenhouseKtApplication

fun main(args: Array<String>) {
	runApplication<GreenhouseKtApplication>(*args)
}
