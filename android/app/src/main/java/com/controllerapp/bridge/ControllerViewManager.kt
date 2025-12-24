package com.controllerapp.bridge

import com.controllerapp.controller.ControllerView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class ControllerViewManager : SimpleViewManager<ControllerView>() {
    override fun getName(): String = "NativeControllerView"

    override fun createViewInstance(reactContext: ThemedReactContext): ControllerView {
        val view = ControllerView(reactContext)
        view.attachEmitter(
            ControllerEventEmitter(reactContext)
        )
        return view
    }
}