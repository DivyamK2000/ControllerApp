package com.controllerapp.controller.math

data class Vector2(
    car x: Float = 0f,
    var y: Float = 0f
) {
    fun length(): Float = sqrt(x*x + y*y)

    fun normalize(): Vector2 {
        val len = length()
        if (len > 0f) {
            x /= len
            y /= len
        }
        return this
    }

    operator fun minus(other: Vector2): Vector2 = Vector2(x-other.x, y-other.y)

    operator fun times(value: Float): Vector2 = Vector2(x*value, y*value)
}