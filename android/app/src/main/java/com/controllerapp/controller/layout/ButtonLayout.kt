package com.controllerapp.controller.layout

import com.controllerapp.controller.input.ButtonType

data class ButtonLayout(
    val type: ButtonType,
    val x: Float,
    val y: Float,
    val radius: Float
)