package dev.pigmeo.greenhouse_kt.domain.services

import dev.pigmeo.greenhouse_kt.domain.entities.User

interface UserService {
    fun setupAdmin(password: String): User
}