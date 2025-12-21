package com.controllerapp.controller.math

object Interpolator {
    fun lerp(
        from: Vector2,
        to: Vector2,
        alpha: Float
    ): Vector2 {
        return Vector2(
            from.x + (to.x - from.x) * alpha
            from.y + (to.y - from.y) * alpha
        )
    }
}