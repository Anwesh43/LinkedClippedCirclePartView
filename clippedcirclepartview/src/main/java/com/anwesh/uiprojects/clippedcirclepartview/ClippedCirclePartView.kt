package com.anwesh.uiprojects.clippedcirclepartview

/**
 * Created by anweshmishra on 06/04/20.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val nodes : Int = 5
val parts : Int = 5
val scGap : Float = 0.02f
val delay : Long = 20
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#3F51B5")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawClippedCirclePart(i : Int, size : Float, scale : Float, w : Float, paint : Paint) {
    val hPart = 2 * size / parts
    val y : Float = hPart * i
    val sf : Float = scale.sinify().divideScale(i, parts)
    save()
    translate(size + (w - size - size) * sf, -size)
    clipRect(RectF(-size, y, size, y + hPart))
    drawCircle(0f, 0f, size, paint)
    restore()
}

fun Canvas.drawClippedCircleParts(size : Float, scale : Float, w : Float, paint : Paint) {
    for (j in 0..(parts - 1)) {
        drawClippedCirclePart(j, size, scale, w, paint)
    }
}

fun Canvas.drawCCPNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    save()
    translate(0f, gap * (i + 1))
    drawClippedCircleParts(size, scale, w, paint)
    restore()
}

class ClippedCirclePartView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}