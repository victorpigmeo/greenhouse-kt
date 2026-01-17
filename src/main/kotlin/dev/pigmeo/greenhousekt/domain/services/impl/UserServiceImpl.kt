package dev.pigmeo.greenhousekt.domain.services.impl

import dev.pigmeo.greenhousekt.domain.entities.User
import dev.pigmeo.greenhousekt.domain.exceptions.AdminUserAlreadySetupException
import dev.pigmeo.greenhousekt.domain.services.UserService
import dev.pigmeo.greenhousekt.infrastructure.repository.UserRepository
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
            throw AdminUserAlreadySetupException("Admin user already setup")
        }

        val user = User(username = "admin", password = this.passwordEncoder.encode(password))

        return this.userRepository.save(user)
    }
}
