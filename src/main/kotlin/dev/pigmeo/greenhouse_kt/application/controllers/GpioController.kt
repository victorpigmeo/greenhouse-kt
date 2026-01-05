package dev.pigmeo.greenhouse_kt.application.controllers

import dev.pigmeo.greenhouse_kt.application.mappers.GpioMapper
import dev.pigmeo.greenhouse_kt.application.payloads.GpioDtoIn
import dev.pigmeo.greenhouse_kt.application.payloads.GpioDtoOut
import dev.pigmeo.greenhouse_kt.domain.entities.Gpio
import dev.pigmeo.greenhouse_kt.domain.services.GpioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/gpio")
class GpioController(
    private val gpioService: GpioService,
    private val gpioMapper: GpioMapper,
) {

    @GetMapping
    fun getGpio(): ResponseEntity<List<Gpio>> {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping
    fun createGpio(@RequestBody gpioDtoIn: GpioDtoIn): ResponseEntity<GpioDtoOut> {
        return ResponseEntity.ok()
            .body(
                this.gpioMapper.mapToOut(
                    this.gpioService.newGpio(gpioMapper.mapToDomain(gpioDtoIn))
                )
            );
    }

    @PatchMapping("/{id}")
    fun useGpio(@PathVariable id: Long): ResponseEntity<Gpio> {
        return ResponseEntity.ok().body(this.gpioService.useGpio(id));
    }
}