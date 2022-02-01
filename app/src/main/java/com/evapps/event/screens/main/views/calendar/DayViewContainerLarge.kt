package com.evapps.event.screens.main.views.calendar


import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.evapps.event.databinding.LargeCalendarDayLayoutBinding
import com.evapps.event.screens.main.views.DateController
import com.kizitonwose.calendarview.CalendarView


class DayViewContainerLarge(
    calendarView: CalendarView,
    view: View,
    dateController: DateController
) : DayViewContainer(view, dateController) {

    override var calendarView: CalendarView = calendarView
    var binding = LargeCalendarDayLayoutBinding.bind(view)

    override fun initViews() {
        binding.calendarDayText.text = dateFormatter.format(day.date)
        binding.exSevenDayText.text = dayFormatter.format(day.date)
    }

    override fun setAppearance(@DrawableRes background: Int, @ColorRes textColor: Int) {
        binding.exSevenSelectedView.setImageDrawable(
            context?.getDrawable(background)
        )
        context?.getColor(textColor)?.let(::setColorForText)
    }

    private fun setColorForText(@ColorInt color: Int) {
        binding.calendarDayText.setTextColor(color)
        binding.exSevenDayText.setTextColor(color)
    }

}