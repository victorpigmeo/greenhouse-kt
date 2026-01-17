package dev.pigmeo.greenhousekt.domain.services

import dev.pigmeo.greenhousekt.domain.entities.DhtRead

interface DhtService {
    fun getLiveDhtRead(): DhtRead
}
