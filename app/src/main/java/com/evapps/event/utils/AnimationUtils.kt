package com.evapps.event.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.util.Log
import android.view.View
import com.evapps.event.extensions.ViewExtensions.setAnimationOnClick

object AnimationUtils {
    fun View.shrink() {
        val pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, FULL_SIZE, SHRINK_SIZE)
        val pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, FULL_SIZE, SHRINK_SIZE)
        val scaleAnimation: ObjectAnimator =
            ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY)
        val animatorSet = AnimatorSet()
        animatorSet.duration = SCALING_TIME

        animatorSet.play(scaleAnimation)
        animatorSet.start()
    }

    fun View.enlarge(startScale: Float = SHRINK_SIZE): AnimatorSet {
        val pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, this.scaleX, FULL_SIZE)
        val pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, this.scaleY, FULL_SIZE)
        val scaleAnimation: ObjectAnimator =
            ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY)
        val animatorSet = AnimatorSet()
        animatorSet.duration = SCALING_TIME
        animatorSet.interpolator = BounceInterpolator(BOUNCE_AMPLITUDE, BOUNCE_FREQUENCY)
        animatorSet.play(scaleAnimation)
        animatorSet.start()
        return animatorSet
    }

    const val SHRINK_SIZE = 0.8f
    const val FULL_SIZE = 1.0f
    const val SCALING_TIME = 200L
    const val BOUNCE_AMPLITUDE = 0.2
    const val BOUNCE_FREQUENCY = 15.0

}