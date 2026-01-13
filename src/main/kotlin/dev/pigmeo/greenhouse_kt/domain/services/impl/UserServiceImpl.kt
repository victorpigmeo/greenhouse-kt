package dev.pigmeo.greenhouse_kt.domain.services.impl

import dev.pigmeo.greenhouse_kt.domain.entities.User
import dev.pigmeo.greenhouse_kt.domain.services.UserService
import dev.pigmeo.greenhouse_kt.infrastructure.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserService {
    override fun setupAdmin(password: String): User {
        val adminUser = this.userRepository.findByUsername("admin")

        if (adminUser != null) {
            throw RuntimeException("Admin user already setup")
        }

        val user = User(username = "admin", password = this.passwordEncoder.encode(password))

        return this.userRepository.save(user)
    }
}