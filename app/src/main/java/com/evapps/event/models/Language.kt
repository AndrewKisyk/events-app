package com.evapps.event.models

import com.evapps.event.R

class Language(val icon: Int, val name: Int, val countryCode:String) {
    companion object
    {
        val languages: Array<Language> = arrayOf(
            Language(
                R.drawable.great_britan_flag,
                R.string.english,
                "en"
            ),
            Language(
                R.drawable.russian_flag,
                R.string.russian,
                "ru"
            )
        )
    }
    fun swipeLanguages(){
        languages.reverse()
    }
}