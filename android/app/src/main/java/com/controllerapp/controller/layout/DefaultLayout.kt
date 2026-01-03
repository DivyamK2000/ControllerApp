package com.controllerapp.controller.layout

object DefaultLayout {
    fun create(width: int, height: Int): ControllerLayout {
        return ControllerLayout(
            leftStick = StickLayout(
                x = width * 0.25f,
                y = height * 0.7f,
                radius = width * 0.18f
            ),
            rightStick = StickLayout(
                x = width * 0.75f,
                y = height * 0.7f,
                radius = width * 0.18f
            ),
            dPad = DPadLayout(
                x = width * 0.2f,
                y = height * 0.6f,
                size = width * 0.1f
            ),
            buttons = listOf(
                ButtonLayout(ButtonType.A, width * 0.8f, height * 0.6f, width * 0.06f),
                ButtonLayout(ButtonType.B, width * 0.9f, height * 0.5f, width * 0.06f),
                ButtonLayout(ButtonType.X, width * 0.7f, height * 0.5f, width * 0.06f),
                ButtonLayout(ButtonType.Y, width * 0.8f, height * 0.4f, width * 0.06f)
            )
        )
    }
}