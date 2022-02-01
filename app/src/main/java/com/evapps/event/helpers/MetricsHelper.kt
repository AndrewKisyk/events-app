package com.evapps.event.helpers

import android.content.res.Resources
import android.util.TypedValue

class MetricsHelper {
    companion object {
        val Number.toPx
            get() = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                this.toFloat(),
                Resources.getSystem().displayMetrics
            )
    }
}