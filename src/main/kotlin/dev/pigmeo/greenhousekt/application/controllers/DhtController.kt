package dev.pigmeo.greenhousekt.application.controllers

import dev.pigmeo.greenhousekt.application.mappers.DhtMapper
import dev.pigmeo.greenhousekt.application.payloads.DhtReadDtoOut
import dev.pigmeo.greenhousekt.domain.services.DhtService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/dht")
class DhtController(
    private val dhtMapper: DhtMapper,
    private val dhtService: DhtService,
) {
    @GetMapping("/live")
    fun getLiveDhtRead(): ResponseEntity<DhtReadDtoOut> {
        return ResponseEntity.ok().body(
            this.dhtMapper.mapToOut(
                this.dhtService.getLiveDhtRead()
            )
        )
    }
}
