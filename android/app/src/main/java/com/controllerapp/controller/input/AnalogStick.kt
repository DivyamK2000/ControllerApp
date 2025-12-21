package com.controllerapp.controller.input

import com.controllerapp.controller.math.DeadZone
import com.controllerapp.controller.math.Interpolator
import com.controllerapp.controller.math.Vector2

class AnalogStick(
    private val center: Vector2,
    private val radius: Float,
    private val deadZone: Float = 0.15f,
    private val smoothing: Float = 0.18f
): InputTarget {
    private var pointerId: Int? = null

    private val raw = Vector2()
    private var output = Vector2()
    private var smoothed = Vector2()

    override fun contains(x: Float, y: Float): Boolean {
        val dx = x - center.x
        val dy = y - center.y
        return dx * dx + dy * dy <= radius * radius
    }

    override fun onTouchDown(pointerId: Int, x: Float, y: Float) {
        this.pointerId = pointerId
        update(x, y)
    }

    override fun onTouchMove(pointerId: Int, x: Float, y: Float) {
        if (this.pointerId == pointerId) {
            update(x, y)
        }
    }

    override fun onTouchUp(pointerId: Int) {
        if (this.pointerId == pointerId) {
            this.pointerId = null
            raw.x = 0f
            raw.y = 0f
        }
    }

    private fun update(X: Float, y: Float) {
        raw.x = x - center.x
        raw.y = y - center.y

        raw.clamp(radius)

        val normalized = Vector2(
            raw.x / radius,
            raw.y / radius
        )

        output = DeadZone.apply(normalized, deadZone)
        smoothed = Interpolator.lerp(smoothed, output, smoothing)
    }

    fun value(): Vector2 = smoothed
}