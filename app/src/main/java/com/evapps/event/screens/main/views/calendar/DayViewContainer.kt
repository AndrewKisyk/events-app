package com.evapps.event.screens.main.views.calendar

import android.content.Context
import android.view.View
import com.evapps.event.R

import com.evapps.event.databinding.SearchFragmentBinding
import com.evapps.event.screens.main.views.DateController
import com.kizitonwose.calendarview.CalendarView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

abstract class DayViewContainer(
    view: View,
    dateController: DateController
) : ViewContainer(view), DateViewUi {
    val dateFormatter = DateTimeFormatter.ofPattern("dd")
    val dayFormatter = DateTimeFormatter.ofPattern("EEE")
    val monthFormatter = DateTimeFormatter.ofPattern("MMM")
    protected var context: Context? = null
    protected var currentDate = LocalDate.now()
    lateinit var day: CalendarDay

    abstract var calendarView: CalendarView

    init {
        context = view.rootView.context
        view.setOnClickListener {
            val firstDay = calendarView.findFirstVisibleDay()
            val lastDay = calendarView.findLastVisibleDay()
            if (firstDay == day) {
                // If the first date on screen was clicked, we scroll to the date to ensure
                // it is fully visible if it was partially off the screen when clicked.
               calendarView.smoothScrollToDate(day.date)
            } else if (lastDay == day) {
                // If the last date was clicked, we scroll to 4 days ago, this forces the
                // clicked date to be fully visible if it was partially off the screen.
                // We scroll to 4 days ago because we show max of five days on the screen
                // so scrolling to 4 days ago brings the clicked date into full visibility
                // at the end of the calendar view.
                calendarView.smoothScrollToDate(day.date.minusDays(3))
            }

            // Example: If you want the clicked date to always be centered on the screen,
            // you would use: exSevenCalendar.smoothScrollToDate(day.date.minusDays(2))

            if (dateController.getSelectedDate() != day.date) {
                val oldDate = dateController.getSelectedDate()
                dateController.selectDate(day.date)
                calendarView.notifyDateChanged(day.date)
                oldDate?.let { calendarView.notifyDateChanged(it) }
            }
        }
    }

    fun bind(day: CalendarDay, dateController: DateController) {
        this.day = day
        initViews()
        when (day.date) {
            currentDate -> setUpCurrentDayView()
            dateController.getSelectedDate() -> setUpSelectedDayView()
            else -> {
                if (day.date.isBefore(currentDate)) setUpPreviousDaysView()
                else setUpNextDaysView()
            }
        }
    }

    override fun setUpNextDaysView() {
        setAppearance(R.drawable.selected_ripple, R.color.secondText)
    }

    override fun setUpPreviousDaysView() {
        setAppearance(R.drawable.selected_ripple, R.color.gray)
    }

    override fun setUpCurrentDayView() {
        setAppearance(R.drawable.selected_gray, R.color.colorPrimary)
    }

    override fun setUpSelectedDayView() {
        setAppearance(R.drawable.selected_yellow, R.color.white)
    }

}