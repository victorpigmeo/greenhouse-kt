package dev.pigmeo.greenhouse_kt.domain.services.impl

import dev.pigmeo.greenhouse_kt.application.http_client.EspClient
import dev.pigmeo.greenhouse_kt.application.mappers.GpioMapper
import dev.pigmeo.greenhouse_kt.domain.entities.Gpio
import dev.pigmeo.greenhouse_kt.domain.services.GpioService
import dev.pigmeo.greenhouse_kt.infrastructure.repository.GpioRepository
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
            ?: throw RuntimeException("Gpio with id $gpioId was not found on the database")

        gpio.use()

        this.espClient.useGpio(this.gpioMapper.mapToEspOut(gpio))

        this.gpioRepository.save(gpio)

        return gpio
    }
}