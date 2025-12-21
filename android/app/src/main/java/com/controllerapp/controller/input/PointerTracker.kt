package com.controllerapp.controller.input

class PointerTracker {
    private val pointerMap = mutableMapOf<Int, InputTarget>()

    fun assign(pointerId: Int, target: InputTarget) {
        pointerMap[pointerId] = target
    }

    fun get(pointerId: Int): InputTarget? {
        return pointerMap[pointerId]
    }

    fun release(pointerId: Int) {
        pointerMap.remove(pointerId)
    }

    fun clear() {
        pointerMap.clear()
    }
}