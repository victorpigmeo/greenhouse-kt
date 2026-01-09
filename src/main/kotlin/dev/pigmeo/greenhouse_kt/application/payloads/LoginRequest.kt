package dev.pigmeo.greenhouse_kt.application.payloads

data class LoginRequest(
    val username: String,
    val password: String
)