package dev.pigmeo.greenhousekt.domain.services.impl

import dev.pigmeo.greenhousekt.application.httpclient.EspClient
import dev.pigmeo.greenhousekt.application.mappers.GpioMapper
import dev.pigmeo.greenhousekt.domain.entities.Gpio
import dev.pigmeo.greenhousekt.domain.exceptions.GpioNotFoundException
import dev.pigmeo.greenhousekt.domain.services.GpioService
import dev.pigmeo.greenhousekt.infrastructure.repository.GpioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class GpioServiceImpl(
    private val gpioRepository: GpioRepository,
    private val espClient: EspClient,
    private val gpioMapper: GpioMapper
) : GpioService {
    override fun getAllGpio(): List<Gpio> {
        return gpioRepository.findAll()
    }

    @Transactional
    override fun newGpio(gpio: Gpio): Gpio {
        return this.gpioRepository.save(gpio)
    }

    @Transactional
    override fun useGpio(gpioId: Long): Gpio {
        val gpio = this.gpioRepository.findById(gpioId).getOrNull()
            ?: throw GpioNotFoundException("Gpio with id $gpioId was not found on the database")

        gpio.use()

        this.espClient.useGpio(this.gpioMapper.mapToEspOut(gpio))

        this.gpioRepository.save(gpio)

        return gpio
    }
}
