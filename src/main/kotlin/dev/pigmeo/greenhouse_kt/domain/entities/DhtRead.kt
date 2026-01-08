package dev.pigmeo.greenhouse_kt.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class DhtRead(
    @field:Column(nullable = false)
    val temperature: Float,

    @field:Column(nullable = false)
    val humidity: Float,

    @field:Column(nullable = false)
    val heatIndex: Float,
): PersistentEntity<Long>()