package com.example.learningapp

import android.app.Application
import timber.log.Timber

class LearningAppApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}