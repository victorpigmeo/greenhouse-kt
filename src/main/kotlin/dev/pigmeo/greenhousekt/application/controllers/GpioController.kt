package dev.pigmeo.greenhousekt.application.controllers

import dev.pigmeo.greenhousekt.application.mappers.GpioMapper
import dev.pigmeo.greenhousekt.application.payloads.GpioDtoIn
import dev.pigmeo.greenhousekt.application.payloads.GpioDtoOut
import dev.pigmeo.greenhousekt.domain.services.GpioService
import dev.pigmeo.greenhousekt.infrastructure.repository.GpioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/gpio")
class GpioController(
    private val gpioService: GpioService,
    private val gpioRepository: GpioRepository,
    private val gpioMapper: GpioMapper,
) {

    @GetMapping
    fun getGpio(): ResponseEntity<List<GpioDtoOut>> {
        return ResponseEntity.ok().body(
            gpioMapper.mapToListOut(this.gpioRepository.findAll())
        )
    }

    @PostMapping
    fun createGpio(@RequestBody gpioDtoIn: GpioDtoIn): ResponseEntity<GpioDtoOut> {
        return ResponseEntity.ok()
            .body(
                this.gpioMapper.mapToOut(
                    this.gpioService.newGpio(gpioMapper.mapToDomain(gpioDtoIn))
                )
            )
    }

    @PatchMapping("/{id}")
    fun useGpio(@PathVariable id: Long): ResponseEntity<GpioDtoOut> {
        return ResponseEntity.ok().body(
            this.gpioMapper.mapToOut(
                this.gpioService.useGpio(id)
            )
        )
    }
}
