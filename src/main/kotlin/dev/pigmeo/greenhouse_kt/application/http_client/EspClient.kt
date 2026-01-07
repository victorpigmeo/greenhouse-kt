package dev.pigmeo.greenhouse_kt.application.http_client

import dev.pigmeo.greenhouse_kt.application.payloads.GpioEspDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioEspDtoOut
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class EspClient(
    @Qualifier("espHttpClient") private val espHttpClient: WebClient
) {

    //    @CircuitBreaker
    fun useGpio(gpioDtoOut: GpioEspDtoOut) {
        println("/gpio/${gpioDtoOut.actionValue}/${gpioDtoOut.pin}/${gpioDtoOut.stateValue}")

        val response: GpioEspDtoIn = this.espHttpClient.get()
            .uri("/gpio/${gpioDtoOut.actionValue}/${gpioDtoOut.pin}/${gpioDtoOut.stateValue}")
            .retrieve()
            .bodyToMono<GpioEspDtoIn>()
            .block() ?: throw RuntimeException("Impossible to reach ESP")

        if(response.value.toString() != gpioDtoOut.stateValue){
            throw IllegalStateException("ESP returned invalid state")
        }
    }
}