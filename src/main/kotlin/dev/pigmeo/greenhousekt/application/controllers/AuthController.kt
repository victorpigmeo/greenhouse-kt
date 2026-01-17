package dev.pigmeo.greenhousekt.application.controllers

import dev.pigmeo.greenhousekt.application.mappers.UserMapper
import dev.pigmeo.greenhousekt.application.payloads.LoginRequest
import dev.pigmeo.greenhousekt.application.payloads.UserDtoIn
import dev.pigmeo.greenhousekt.application.payloads.UserDtoOut
import dev.pigmeo.greenhousekt.domain.services.JwtTokenService
import dev.pigmeo.greenhousekt.domain.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenService: JwtTokenService,
    private val userService: UserService,
    private val userMapper: UserMapper
) {
    @PostMapping("/admin")
    fun setupAdmin(@RequestBody userDtoIn: UserDtoIn): ResponseEntity<UserDtoOut> {
        return ResponseEntity.ok()
            .body(this.userMapper.mapToOut(this.userService.setupAdmin(userDtoIn.password)))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )

        val token = jwtTokenService.generateToken(auth)

        return ResponseEntity.ok().body(token)
    }
}
