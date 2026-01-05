package dev.pigmeo.greenhouse_kt.infrastructure.repository

import dev.pigmeo.greenhouse_kt.domain.entities.Gpio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GpioRepository: JpaRepository<Gpio, Long> {
}