package dev.pigmeo.greenhouse_kt.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class DhtRead(
    @field:Column(nullable = false)
    var temperature: Float,

    @field:Column(nullable = false)
    var humidity: Float,

    @field:Column(nullable = false)
    var heatIndex: Float,
): PersistentEntity<Long>()