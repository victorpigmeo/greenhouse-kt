package dev.pigmeo.greenhouse_kt.application.controllers

import dev.pigmeo.greenhouse_kt.application.mappers.UserMapper
import dev.pigmeo.greenhouse_kt.application.payloads.LoginRequest
import dev.pigmeo.greenhouse_kt.application.payloads.UserDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.UserDtoOut
import dev.pigmeo.greenhouse_kt.domain.services.JwtTokenService
import dev.pigmeo.greenhouse_kt.domain.services.UserService
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