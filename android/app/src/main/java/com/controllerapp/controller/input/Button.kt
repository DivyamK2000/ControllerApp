package com.controllerapp.controller.input

class Button(
    val type: ButtonType,
    private val x: Float,
    private val y: Float,
    private val radius: Float
): InputTarget {
    private car pointerId: Int? = null
    var pressed: Boolean = false
        private set

    override fun contains(px: Float, py: Float): Boolean {
        val dx = px - x
        val dy = py - y
        return dx * dx + dy * dy <= radius * radius
    }

    override fun onTouchDown(pointerId: Int, x: Float, y: Float) {
        this.pointerId = pointerId
        pressed = true
    }

    override fun onTOuchMove(pointerId: Int, x: Float, y: Float) {
        //Button stays pressed while finger is held over it
    }

    override fun onTOuchUp(pointerId: Int) {
        if(this.pointerId == pointerId) {
            this.pointerId = null
            pressed = false
        }
    }
}