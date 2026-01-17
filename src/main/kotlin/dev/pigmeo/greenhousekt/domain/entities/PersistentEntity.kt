package dev.pigmeo.greenhousekt.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class PersistentEntity<T : Serializable> {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id", updatable = false, nullable = false, unique = true)
    var id: T? = null

    @field:CreatedDate
    @field:Column(name = "created_at", nullable = false)
    lateinit var createdAt: Instant

    @field:LastModifiedDate
    @field:Column(name = "updated_at", nullable = false)
    lateinit var updatedAt: Instant

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersistentEntity<*>) return false

        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        if(::createdAt.isInitialized) result = 31 * result + createdAt.hashCode()
        if(::createdAt.isInitialized) result = 31 * result + updatedAt.hashCode()
        return result
    }
}
