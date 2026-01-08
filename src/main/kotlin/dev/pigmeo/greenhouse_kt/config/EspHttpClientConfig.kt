package dev.pigmeo.greenhouse_kt.config

import io.netty.channel.ChannelOption
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class EspHttpClientConfig {

    @Bean
    @Qualifier("espClient")
    fun espHttpClient(): WebClient{
        val httpClient: HttpClient = HttpClient.create()
            .baseUrl("http://192.168.18.26:8080/api")
            .responseTimeout(Duration.ofSeconds(3))
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}