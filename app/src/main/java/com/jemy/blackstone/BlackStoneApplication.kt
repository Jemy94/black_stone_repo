package com.jemy.blackstone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BlackStoneApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}