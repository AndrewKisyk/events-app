package com.evapps.event.extensions

import android.annotation.SuppressLint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.animation.addListener
import com.evapps.event.utils.AnimationUtils
import com.evapps.event.utils.AnimationUtils.enlarge
import com.evapps.event.utils.AnimationUtils.shrink


object ViewExtensions {
    @SuppressLint("ClickableViewAccessibility")
    fun View.setAnimationOnClick(listener: () -> Unit) {
        var rect: Rect? = null
        var isEnlarged = true
        this.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    rect = Rect(v!!.left, v.top, v.right, v.bottom)
                    isEnlarged = false
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    this.shrink()
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_MOVE   -> {
                    if (event.action == MotionEvent.ACTION_MOVE) {
                        val isInside = rect?.contains(
                            v.left + event.x.toInt(),
                            v.top + event.y.toInt()
                        ) ?: false
                        if (isInside || isEnlarged) return@setOnTouchListener true
                    }
                    v.parent.requestDisallowInterceptTouchEvent(false)
                    this.enlarge().addListener(onEnd = {
                        listener.invoke()
                    })
                    isEnlarged = true
                }
            }
            return@setOnTouchListener true
        }
    }

}

//when (event.action) {
//    MotionEvent.ACTION_DOWN -> {
//        this.shrink()
//        return true
//    }
//    MotionEvent.ACTION_UP, MotionEvent.ACTION_OUTSIDE -> {
//        var startScale = SHRINK_SIZE
//        val pressingTime = (event.eventTime - event.downTime).toDouble()
//        if (pressingTime < SCALING_TIME) {
//            startScale = 1 - (pressingTime / 1000).toFloat()
//        }
//        this.enlarge(startScale).addListener(onEnd = {
//            listener.invoke()
//        })
//        return true
//    }
//}