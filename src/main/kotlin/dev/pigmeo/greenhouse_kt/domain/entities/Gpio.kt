package dev.pigmeo.greenhouse_kt.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity

enum class GpioState(val value: String) {
    ZERO("0"),
    ONE("1"),
    PRESSED("pressed")
}

enum class GpioType(val value: String) {
    SWITCH("set"),
    BUTTON("button")
}

@Entity
class Gpio(
    @field:Column(name = "pin", nullable = false, unique = true)
    val pin: Byte,

    @field:Column(name = "type", nullable = false)
    val type: GpioType,

    @field:Column(name = "state", nullable = false)
    var state: GpioState,
) : PersistentEntity<Long>() {

    fun use() {
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
    }
}