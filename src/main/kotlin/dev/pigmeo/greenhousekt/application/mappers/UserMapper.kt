package dev.pigmeo.greenhousekt.application.mappers

import dev.pigmeo.greenhousekt.application.payloads.UserDtoOut
import dev.pigmeo.greenhousekt.domain.entities.User
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Component
@Mapper(config = MapperConfig::class)
interface UserMapper {
    fun mapToOut(user: User): UserDtoOut
}
