package dev.pigmeo.greenhouse_kt.infrastructure.repository

import dev.pigmeo.greenhouse_kt.domain.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findByUsername(username: String): User?
}