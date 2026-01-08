package dev.pigmeo.greenhouse_kt.application.http_client

import dev.pigmeo.greenhouse_kt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioEspDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioEspDtoOut
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class EspClient(
    @Qualifier("espHttpClient") private val espHttpClient: WebClient
) {

    fun useGpio(gpioDtoOut: GpioEspDtoOut) {
        val response: GpioEspDtoIn = this.espHttpClient.get()
            .uri("/gpio/${gpioDtoOut.actionValue}/${gpioDtoOut.pin}/${gpioDtoOut.stateValue}")
            .retrieve()
            .bodyToMono<GpioEspDtoIn>()
            .block() ?: throw RuntimeException("Impossible to reach ESP")

        if(response.value.toString() != gpioDtoOut.stateValue){
            throw IllegalStateException("ESP returned invalid state")
        }
    }

    @CircuitBreaker(name = "esp-dht")
    fun getDhtRead(): DhtReadEspDtoIn {
        return this.espHttpClient.get()
            .uri("/dht")
            .retrieve()
            .bodyToMono<DhtReadEspDtoIn>()
            .block() ?: throw RuntimeException("Impossible to reach ESP")
    }
}