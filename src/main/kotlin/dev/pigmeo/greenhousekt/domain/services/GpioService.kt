package dev.pigmeo.greenhousekt.domain.services

import dev.pigmeo.greenhousekt.domain.entities.Gpio

interface GpioService {
    fun getAllGpio(): List<Gpio>
    fun newGpio(gpio: Gpio): Gpio
    fun useGpio(gpioId: Long): Gpio
}
