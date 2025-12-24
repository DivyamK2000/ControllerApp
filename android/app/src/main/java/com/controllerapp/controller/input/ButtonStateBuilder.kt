package com.controllerapp.controller.input

class ButtonStateBuilder {
    private var mask: Int = 0

    fun set(type: ButtonType, pressed: Boolean) {
        mask = if (pressed) {
            mask or (1 shl type.bit)
        } else {
            mask or (1 shl type.bit).inv()
        }
    }

    fun value(): Int = mask

    fun reset() {
        mask = 0
    }
}