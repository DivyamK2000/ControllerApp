package com.controllerapp.controller.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import com.controllerapp.bridge.ControllerEventEmitter
import com.controllerapp.controller.input.*
import com.controllerapp.controller.input.AnalogStick
import com.controllerapp.controller.input.InputProcessor
import com.controllerapp.controller.state.ControllerState
import com.controllerapp.controller.math.Vector2

class ControllerView(context: Context) : View(context) {
    // Paint -------------------------------------------

    private val bgPaint = Paint().apply {
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
    private lateinit var dPad: DPad
    private lateinit var buttons: List<Button> 
    private lateinit var inputProcessor: InputProcessor
    private lateinit var eventEmitter: ControllerEventEmitter
    private val buttonState = ButtonStateBuilder()

    private var lastEmitTime = 0L
    private val emitIntervalMs = 16L

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

        dPad = DPad(
            centerX = width * 0.2f,
            centerY = height * 0.6f,
            size = width * 0.1f
        )

        buttons = listOf(
            Button(ButtonType.A, width * 0.8f, height * 0.6f, width * 0.06f),
            Button(ButtonType.B, width * 0.9f, height * 0.5f, width * 0.06f),
            Button(ButtonType.X, width * 0.7f, height * 0.5f, width * 0.06f),
            Button(ButtonType.Y, width * 0.8f, height * 0.4f, width * 0.06f),
        )

        InputProcessor = InputProcessor(
            listOf(leftStick, rightStick, dPad) + buttons
        )
    }

    //Touch Handling------------------------------------

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val index = event.actionIndex
        val pointerId = event.getPointerId(index)

        when (action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_POINTER_DOWN -> {
                inputProcessor.onPointerDown(
                    pointerId,
                    event.getX(index),
                    event.getY(index)
                )
            }

            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    inputProcessor.onPointerMove(
                        event.getPointerId(i),
                        event.getX(i),
                        event.getY(i)
                    )
                }
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_POINTER_UP,
            MotionEvent.ACTION_CANCEL -> {
                inputProcessor.onPointerUP(pointerId)
            }
        }

        return true
    }

    fun attachEmitter(emitter: ControllerEventEmitter){
        this.eventEmitter = emitter
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
            50f, 100f, debugPaint
        )

        canvas.drawText(
            "R: ${"%.2f".format(right.x)}, ${"%.2f".format(right.y)}",
            50f, 100f, debugPaint
        )

        //Continous Redraw
        emitStateIfNeeded()
        invalidate()
    }

    //Emit State------------------------------------------

    private fun emitStateIfNeeded() {
        val now = currentTimeMillis()
        if (now - lastEmitTime < emitIntervalMs) return lastEmitTime = now

        buttonState.reset()

        for (button in buttons) {
            buttonState.set(button.type, button.pressed)
        }

        //D-Pad
        buttonState.set(ButtonType.DPAD_UP, dPad.isPressed(DPadDirection.UP))
        buttonState.set(ButtonType.DPAD_DOWN, dPad.isPressed(DPadDirection.DOWN))
        buttonState.set(ButtonType.DPAD_LEFT, dPad.isPressed(DPadDirection.LEFT))
        buttonState.set(ButtonType.DPAD_RIGHT, dPad.isPressed(DPadDirection.RIGHT))

        val left = leftStick.value()
        val right = rightStick.value()

        val state = ControllerState(
            lx = left.x,
            ly = left.y,
            rx = right.x,
            ry = right.y,
            buttons = buttonState.value()

            eventEmitter.emitState(state)
        )
    }
}
