package dev.pigmeo.greenhouse_kt.domain.services

import dev.pigmeo.greenhouse_kt.domain.entities.DhtRead

interface DhtService {
    fun getLiveDhtRead(): DhtRead;
}