package com.evapps.event.utils

import android.view.View
import android.view.ViewGroup

import android.animation.ValueAnimator
import android.util.Log
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.evapps.event.screens.main.views.calendar.CalendarBarState
import kotlin.math.max


class CalendarBarAnimationHelper {
    companion object {
        private val VISIBLE: Float = 1f
        private val INVISIBLE: Float = 0f
        private val VISIBLATY_DECREASE: Float = 0.1f
    }

    private var maxHeight: Int = 0
    private var minHeight: Int = 0
    private var containerMaxHeight: Int = 0
    private var containerMinHeight: Int = 0
    private var middleHeight: Int? = null
    private var middleHeightPercent: Float? = null
    private var layoutParams: ConstraintLayout.LayoutParams? = null
    private var calendarBarState: CalendarBarState = CalendarBarState.COLLAPSED
    fun setCalendarBarParams(containerView: View, minHeight: Int, maxHeight: Int) {
        this.maxHeight = maxHeight
        this.minHeight = minHeight
        Log.e("minHeight", minHeight.toString())
        Log.e("maxHeight", maxHeight.toString())
        containerMinHeight = containerView.measuredHeight
        containerMaxHeight = containerMinHeight - minHeight + maxHeight
        Log.e("containerMinHeight", containerMinHeight.toString())
        Log.e("containerMaxHeight", containerMaxHeight.toString())
        middleHeight = getMiddleHeight()
        middleHeightPercent = middleHeight?.toFloat()?.div(100)
    }

    fun resize(containerView: View, calendarView: View, heightGain: Int) {
        var newContainerHeight = containerView.height + heightGain
        var newCalendarHeight = (calendarView.height + (heightGain * 1)).toInt()        //702
        Log.e("New calendar height", newCalendarHeight.toString())
        if (newContainerHeight < containerMinHeight!!) {
            newContainerHeight = containerMinHeight!!

            calendarBarState = CalendarBarState.COLLAPSED
        }
        if (newContainerHeight > containerMaxHeight!!) {
            newContainerHeight = containerMaxHeight!!
            newCalendarHeight = maxHeight!!
            calendarBarState = CalendarBarState.EXPANDED
        }

        if (newCalendarHeight < minHeight) {
            newCalendarHeight = minHeight!!
        }

        if (newCalendarHeight > maxHeight) {
            newCalendarHeight = maxHeight!!
        }

        resizeContainer(containerView, newContainerHeight)
        resizeContainer(calendarView, newCalendarHeight)
    }

    fun completeIsNeeded(view: View): Boolean {
        return view.height != minHeight && view.height != maxHeight
    }

    fun completeResizing(
        calendarView: View,
        containerView: View,
        doOnEnd: (state: CalendarBarState) -> Unit
    ) {
        var calendarTargetHeight: Int
        var containerTargetHeight: Int
        if (containerView.height > getMiddleHeight()) {
            calendarBarState = CalendarBarState.EXPANDED
            calendarTargetHeight = maxHeight
            containerTargetHeight = containerMaxHeight
        } else {
            calendarBarState = CalendarBarState.COLLAPSED
            calendarTargetHeight = minHeight
            containerTargetHeight = containerMinHeight
        }
        animationResizeView(calendarView, calendarTargetHeight)
        animationResizeView(containerView, containerTargetHeight) { doOnEnd(calendarBarState) }
    }


    private fun animationResizeView(
        view: View,
        newHeight: Int,
        doOnEnd: () -> Unit = {}
    ) {
        val anim = ValueAnimator.ofInt(view.height, newHeight)
        anim.addUpdateListener { valueAnimator ->
            val targetedHeight = valueAnimator.animatedValue as Int
            resizeContainer(view, targetedHeight)
        }
        anim.setDuration(
            (newHeight.div(view.getContext().getResources().getDisplayMetrics().density)).toLong()
        )
        anim.doOnEnd { doOnEnd.invoke() }
        anim.start()
    }

    private fun resizeContainer(view: View, newHeight: Int) {
        val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.height = newHeight
        view.layoutParams = layoutParams
    }

    private fun getMiddleHeight(): Int {
        return containerMaxHeight.div(2)
    }

    fun getCalendarState(): CalendarBarState {
        return calendarBarState
    }

    private fun changeVisibility(containerView: View, fadingView: View) {
        fadingView.alpha = getVisibilityPercent(containerView)
        Log.e("Alpha to set ", getVisibilityPercent(containerView).toString())
    }

    private fun getVisibilityPercent(view: View): Float {
        return if (view.height == minHeight || view.height == maxHeight) {
            VISIBLE
        } else if (view.height < middleHeight!!) {
            minHeight.toFloat().div(view.height).minus(VISIBLATY_DECREASE)
        } else {
            maxHeight.toFloat().div(view.height).minus(VISIBLATY_DECREASE)
        }
    }

}