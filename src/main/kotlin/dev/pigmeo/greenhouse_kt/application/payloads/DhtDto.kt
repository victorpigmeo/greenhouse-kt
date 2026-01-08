package dev.pigmeo.greenhouse_kt.application.payloads

data class DhtReadEspDtoIn(
    val temperature: Float,
    val humidity: Float,
    val heat_index: Float,
)

data class DhtReadDtoOut(
    val temperature: Float,
    val humidity: Float,
    val heatIndex: Float,
)