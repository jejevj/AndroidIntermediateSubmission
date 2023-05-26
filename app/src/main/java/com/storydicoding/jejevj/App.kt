package com.storydicoding.jejevj

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Locator.initWith(this)
    }
}