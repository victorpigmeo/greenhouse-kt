package dev.pigmeo.greenhousekt.application.httpclient

import dev.pigmeo.greenhousekt.application.payloads.DhtReadEspDtoIn
import dev.pigmeo.greenhousekt.application.payloads.GpioEspDtoIn
import dev.pigmeo.greenhousekt.application.payloads.GpioEspDtoOut
import dev.pigmeo.greenhousekt.domain.exceptions.ImpossibleToReachEspException
import dev.pigmeo.greenhousekt.domain.exceptions.InvalidGpioStateException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class EspClient(
    @param:Qualifier("espHttpClient") private val espHttpClient: WebClient
) {

    fun useGpio(gpioDtoOut: GpioEspDtoOut) {
        val response: GpioEspDtoIn = this.espHttpClient.get()
            .uri("/gpio/${gpioDtoOut.actionValue}/${gpioDtoOut.pin}/${gpioDtoOut.stateValue}")
            .retrieve()
            .bodyToMono<GpioEspDtoIn>()
            .block() ?: throw ImpossibleToReachEspException("Impossible to reach ESP")

        if(response.value.toString() != gpioDtoOut.stateValue){
            throw InvalidGpioStateException("ESP returned invalid state")
        }
    }

    @CircuitBreaker(name = "esp-dht")
    fun getDhtRead(): DhtReadEspDtoIn {
        return this.espHttpClient.get()
            .uri("/dht")
            .retrieve()
            .bodyToMono<DhtReadEspDtoIn>()
            .block() ?: throw ImpossibleToReachEspException("Impossible to reach ESP")
    }
}
