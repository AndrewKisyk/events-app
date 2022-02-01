package com.evapps.event.screens.main.views

import java.time.LocalDate

interface DateController {
    fun selectDate(selectedDate: LocalDate)
    fun getSelectedDate(): LocalDate
}
