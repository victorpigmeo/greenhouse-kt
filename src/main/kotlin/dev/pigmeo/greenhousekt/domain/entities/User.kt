package dev.pigmeo.greenhousekt.domain.entities

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
    private var username: String,

    @field:Column(nullable = false)
    private var password: String,
) : PersistentEntity<Long>(), UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> =
        listOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword(): String = password

    override fun getUsername(): String = username
}
