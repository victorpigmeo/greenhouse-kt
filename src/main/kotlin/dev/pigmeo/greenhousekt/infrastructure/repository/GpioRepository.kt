package dev.pigmeo.greenhousekt.infrastructure.repository

import dev.pigmeo.greenhousekt.domain.entities.Gpio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GpioRepository: JpaRepository<Gpio, Long>
