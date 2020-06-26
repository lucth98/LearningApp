package com.example.learningapp

import android.app.Application
import timber.log.Timber

//Application Class
class LearningAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}