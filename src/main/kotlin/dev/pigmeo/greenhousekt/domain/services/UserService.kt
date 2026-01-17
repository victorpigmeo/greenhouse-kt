package dev.pigmeo.greenhousekt.domain.services

import dev.pigmeo.greenhousekt.domain.entities.User

interface UserService {
    fun setupAdmin(password: String): User
}
