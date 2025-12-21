package com.controllerapp.controller.input

interface InputTarget {

    fun contains(x: FLoat, y: FLoat): Boolean

    fun onTouchDown(pointerId; Int, x: Float, y: Float)

    fun onTouchMove(pointerId: Int, x: Float, y: Float)

    fun onTouchUp(pointerId: Int)
}