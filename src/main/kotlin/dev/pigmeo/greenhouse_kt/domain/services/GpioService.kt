package dev.pigmeo.greenhouse_kt.domain.services

import dev.pigmeo.greenhouse_kt.domain.entities.Gpio
import org.springframework.stereotype.Service

interface GpioService {
    fun getAllGpio(): List<Gpio>;
    fun newGpio(gpio: Gpio): Gpio;
    fun useGpio(gpioIdL: Long): Gpio;
}