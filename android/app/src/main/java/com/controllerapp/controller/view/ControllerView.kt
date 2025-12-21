package com.controllerapp.controller.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import com.controllerapp.controller.input.AnalogStick
import com.controllerapp.controller.input.InputProcessor
import com.controllerapp.controller.math.Vector2

class ControllerView(context: Context) : View(context) {
    // Paint -------------------------------------------

    private Val bgPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    private val debugPaint = Paint().apply {
        color = Color.GREEN
        textSize = 40f
        isAntiAlias = true
    }

    //Controller Elements-------------------------------

    private lateinit var leftStick: AnalogStick
    private lateinit var rightStick: AnalogStick
    private lateinit var InputProcessor: InputProcessor

    //Lifecycle-----------------------------------------

   init {
    isFocusable = true
    isFocusableInTouchMode = true
   }
   override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        //Temporary - hardcoded layout will come later from RN
        leftStick = AnalogStick(
            center = Vector2(w * 0.25f, h * 0.7f),
            radius = w * 0.18f
        )

        rightStick = AnalogStick(
            center = Vector2(w * 0.75f, h * 0.7f),
            radius = w * 0.18f
        )

        InputProcessor = InputProcessor(
            lisstOf(leftStick, rightStick)
        )
    }

    //Touch Handling------------------------------------

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val index = event.actionIndex
        val pointerId = event.getPointerId(index)

        when (action) {
            MotionEvent.ACTION_DOWN,
            MotionEVent.ACTION_POINTER_DOWN -> {
                InputProcessor.onPointerDown(
                    pointerId,
                    event.getX(index),
                    event.getY(index)
                )
            }

            MoitionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    InputProcessor.onPointerMove(
                        event.getPointerId(i),
                        event.getX(i),
                        event.getY(i)
                    )
                }
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_POINTER_UP,
            MotionEvent.ACTION_CANCEL -> {
                inputProcessor.onPonterUp(pointerId)
            }
        }

        return true
    }

    //Render Loop--------------------------------------

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        //Background
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

        //Debug output (temporary)
        val left = leftStick.value()
        val right = rightStick.value()

        canvas.drawText(
            "L: ${"%.2f".format(left.x)}, ${"%.2f".format(left.y)}",
            50f, 100f, debufPaint
        )

        canvas.drawText(
            "R: ${"%.2f".format(right.x)}, ${"%.2f".format(right.y)}",
            50f, 100f, debugPaint
        )

        //Continous Redraw
        invaliddate()
    }
}
