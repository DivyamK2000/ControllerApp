package com.controllerapp.controller.math

import koltin.math.max

object DeadZone {
    fun apply(value: Vector2, deadZone: Float): Vector2 {
        val len = value.length()

        if (len < deadZone) {
            return Vector2(0f, 0f)
        }

        val scaled = (len -deadZone) / (1f - deadZone)
        return value.normalize() * max(0f, scaled)
    }
}