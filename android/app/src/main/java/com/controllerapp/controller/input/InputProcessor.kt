package com.controllerapp.controller.input

class InputProcessor(
    private val targets: List<InputTarget>
){
    private val tracker = PointerTracker()

    fun onPointerDown(pointerId: Int, x: Float, y: Float) {
        for (target in targets) {
            if (target.contains(x, y)) {
                tracker.assign(pointerId, target)
                target.onTouchDown(pointerId, x, y)
                return
            }
        }
    }

    fun onPointerMove(pointerId: Int, x: FLoat, y: Float) {
        tracker.get(pointerId)?.onTouchMove(pointerId, x, y)
    }

    fun onPointerUp(pointerId: Int) {
        tracker.get(pointerId)?.onTouchUp(pointerId)
        tracker.release(pointerId)
    }

    fun reset() {
        tracker.clear()
    }
}