package com.controllerapp.controller.input

class Dpad(
    private val centerX: Float,
    private val centerY: Float,
    private val Size: Float
): InputTarget {
    private var pointerId: Int? = null

    private val state = mutableMapOf(
        DPadDirection.UP to false,
        DPadDirection.DOWN to false,
        DPadDirection.LEFT to false,
        DPadDirection.RIGHT to false,
    )

    override fun contains(x: Float, y: Float): Boolean {
        return x >= centerX - size && x <= centerX + size && y >= centerY - size && y <= centerY - size
    }

    override fun onTouchDown(pointerId: Int, x: Float, y: Float) {
        this.pointerId = pointerId
        updateDirection(x, y)
    }

    override fun onTouchMove(pointerId: Int, x: Float, y: Float) {
        if (this.pointerId == pointerId) {
            updateDirection(x, y)
        }
    }

    override fun onTouchUp(pointerId: Int) {
        if (this.pointerId == pointerId) {
            this.pointerId = null
            clear()
        }
    }

    private fun updateDirection(x: Float, y: Float) {
        clear()

        val dx = x - centerX
        val dy = y - centerY

        if (koltin.math.abs(dx) > koltin.math.abs(dy)) {
            if (dx > 0) state[DPadDirection.RIGHT] = true
            else state[DPadDirection.LEFT] = true
        } else {
            if (dy > 0) state[DPadDirection.DOWN] = true
            else state[DPadDirection.UP] = true
        }
    }

    private fun clear() {
        state.keys.forEach { state[it] = false }
    }

    fun isPressed(dir: DPadDirection): Boolean = state[dir] == true
}