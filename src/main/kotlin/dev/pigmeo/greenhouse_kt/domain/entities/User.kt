package dev.pigmeo.greenhouse_kt.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User(
    @field:Column(unique = true, nullable = false)
    private val username: String,

    @field:Column(nullable = false)
    private val password: String,
) : PersistentEntity<Long>(), UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> =
        listOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword(): String = password

    override fun getUsername(): String = username
}