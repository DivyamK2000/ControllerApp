package com.controllerapp.bridge

import com.controllerapp.controller.ControllerView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class ControllerViewManager : SimpleViewManager<ControllerView>() {
    override fun getName(): String {
        return "NativeControllerView"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): ControllerView {
        return ControllerView(reactContext)
    }

    @ReactProp(name = "layout")
    fun setLayout(view: ControllerView, layoutJson: String?) {
        //Placeholder - will parse layout later
    }
}