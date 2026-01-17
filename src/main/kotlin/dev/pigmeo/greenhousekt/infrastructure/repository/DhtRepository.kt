package dev.pigmeo.greenhousekt.infrastructure.repository

import dev.pigmeo.greenhousekt.domain.entities.DhtRead
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DhtRepository: JpaRepository<DhtRead, Long>
