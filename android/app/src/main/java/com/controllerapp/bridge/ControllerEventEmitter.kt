package com.controllerapp.bridge

import com.controllerapp.controller.state.ControllerState
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.modules.core.DeviceEventManagerModule

class ControllerEventEmitter(
    private val reactContext: ReactApplicationContext
) {
    fun emiState(state: ControllerState) {
        val event = mapOf(
            "lx" to state.lx,
            "ly" to state.ly,
            "rx" to state.rx,
            "ry" to state.ry,
            "buttons" to state.buttons
        )

        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit("onControllerState", event)
    }
}