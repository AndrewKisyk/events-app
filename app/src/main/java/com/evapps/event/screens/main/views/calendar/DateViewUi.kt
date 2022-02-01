package com.evapps.event.screens.main.views.calendar

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

interface DateViewUi {
    fun initViews()
    fun setUpSelectedDayView()
    fun setUpCurrentDayView()
    fun setUpPreviousDaysView()
    fun setUpNextDaysView()
    fun setAppearance(@DrawableRes background: Int, @ColorRes textColor: Int)
}