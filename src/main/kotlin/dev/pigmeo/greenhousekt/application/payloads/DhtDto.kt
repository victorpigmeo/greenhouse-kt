package dev.pigmeo.greenhousekt.application.payloads

import com.fasterxml.jackson.annotation.JsonProperty

data class DhtReadEspDtoIn(
    val temperature: Float,
    val humidity: Float,
    @field:JsonProperty("heat_index")
    val heatIndex: Float,
)

data class DhtReadDtoOut(
    val temperature: Float,
    val humidity: Float,
    val heatIndex: Float,
)
