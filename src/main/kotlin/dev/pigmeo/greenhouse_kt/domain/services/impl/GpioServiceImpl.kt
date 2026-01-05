package dev.pigmeo.greenhouse_kt.domain.services.impl

import dev.pigmeo.greenhouse_kt.domain.entities.Gpio
import dev.pigmeo.greenhouse_kt.domain.services.GpioService
import dev.pigmeo.greenhouse_kt.infrastructure.repository.GpioRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class GpioServiceImpl(private val gpioRepository: GpioRepository) : GpioService {
    override fun getAllGpio(): List<Gpio> {
        return gpioRepository.findAll();
    }

    override fun newGpio(gpio: Gpio): Gpio {
        return this.gpioRepository.save(gpio);
    }

    override fun useGpio(gpioIdL: Long): Gpio {
        val gpio = this.gpioRepository.findById(gpioIdL).getOrNull()
            ?: throw RuntimeException("Gpio with id $gpioIdL was not found")

        this.gpioRepository.save(gpio.use());

        return gpio;
    }
}