package com.controllerapp.controller.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class ControllerView(context: Context) : View(context) {

    private Val bgPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

   init {
    isFocusable = true
    isFocusableInTouchMode = true
   }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        //Background
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

        //Render loop placeholder
        invaliddate()
    }
}
