package com.jemy.blackstone.utils

import com.jemy.blackstone.BuildConfig

object Constants {

    const val BASE_URL = "http://data.fixer.io/api/"
    const val API_KEY = "2455796e72b161ab2ec08a46dc69a186"

    object Error {
        const val GENERAL = "generalError"
        const val NO_DATA = "noDataError"
        const val EMPTY_FIELD = "emptyField"
    }

    object PREFS {
        const val NAME = "${BuildConfig.APPLICATION_ID}.prefs"


        object KEY {
            const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
        }
    }
}