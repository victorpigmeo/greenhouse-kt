package dev.pigmeo.greenhouse_kt.infrastructure.repository

import dev.pigmeo.greenhouse_kt.domain.entities.DhtRead
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DhtRepository: JpaRepository<DhtRead, Long>