package dev.pigmeo.greenhouse_kt.application.payloads

data class GpioDtoIn(
    val pin: String,
    val type: String,
    val state: String
)

data class GpioDtoOut(
    val id: Long,
    val updatedAt: String,
    val pin: String,
    val type: String,
    val state: String
)

data class GpioEspDtoIn(
    val pin: Int,
    val value: Int,
)

data class GpioEspDtoOut(
    val id: Long,
    val updatedAt: String,
    val pin: String,
    val actionValue: String,
    val stateValue: String
)