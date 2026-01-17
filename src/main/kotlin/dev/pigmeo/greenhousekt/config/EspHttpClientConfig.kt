package dev.pigmeo.greenhousekt.config

import io.netty.channel.ChannelOption
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class EspHttpClientConfig(
    @param:Value("\${greenhouse.esp.base-url}") private val espBaseUrl: String,
    @param:Value("\${greenhouse.esp.http-timeout-ms}") private val espHttpTimeoutMs: Int
) {

    @Bean
    @Qualifier("espClient")
    fun espHttpClient(): WebClient{
        val httpClient: HttpClient = HttpClient.create()
            .baseUrl(espBaseUrl)
            .responseTimeout(Duration.ofMinutes(espHttpTimeoutMs.toLong()))
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, espHttpTimeoutMs)

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}
