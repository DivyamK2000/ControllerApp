package com.controllerapp.controller.layout

data class ControllerLayout(
    val leftStick: StickLayout,
    val rightStick: StickLayout,
    val buttons: List<ButtonLayout>,
    val dPad: DPadLayout
)