package dev.pigmeo.greenhouse_kt.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class EspHttpClientConfig {

    @Bean
    @Qualifier("espClient")
    fun espHttpClient(): WebClient{
        return WebClient.builder().baseUrl("http://192.168.18.26:8080/api").build()
    }
}