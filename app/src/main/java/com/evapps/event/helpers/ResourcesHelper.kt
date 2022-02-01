package com.evapps.event.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

class ResourcesHelper {
    companion object {
        fun Context.getDrawable(@DrawableRes id: Int): Drawable? {
            return ContextCompat.getDrawable(this, id)
        }

        fun Context.getColor(@ColorRes id: Int): Int? {
            return ContextCompat.getColor(this, id)
        }
    }
}