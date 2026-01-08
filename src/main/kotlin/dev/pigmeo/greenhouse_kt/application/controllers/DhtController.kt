package dev.pigmeo.greenhouse_kt.application.controllers

import dev.pigmeo.greenhouse_kt.application.mappers.DhtMapper
import dev.pigmeo.greenhouse_kt.application.payloads.DhtReadDtoOut
import dev.pigmeo.greenhouse_kt.domain.services.DhtService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/dht")
class DhtController(
    private val dhtMapper: DhtMapper,
    private val dhtService: DhtService
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