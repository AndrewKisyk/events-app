package com.evapps.event.utils

object MathsUtils {
    fun Double.toTenth(): Double {
        var result = this
        while (result > 1) result /= 10
        return result
    }
}