package dev.pigmeo.greenhouse_kt.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity

enum class GpioState {
    ZERO,
    ONE,
    PRESSED
}

enum class GpioType {
    SWITCH,
    BUTTON
}

@Entity
class Gpio(
    @field:Column(name = "pin", nullable = false, unique = true)
    val pin: Byte,

    @field:Column(name = "type", nullable = false)
    val type: GpioType,

    @field:Column(name = "state", nullable = false)
    var state: GpioState
) : PersistentEntity<Long>() {
    fun use(): Gpio{
        when (this.type){
            GpioType.SWITCH -> {
                if(this.state == GpioState.ZERO){
                    this.state = GpioState.ONE
                }else{
                    this.state = GpioState.ZERO
                }
            }
            GpioType.BUTTON -> {
                this.state = GpioState.PRESSED
            }
        }

        return this
    }
}