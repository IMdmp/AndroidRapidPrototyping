package com.imdmp.androidrapidprototype

import android.app.Application
import com.imdmp.androidrapidprototyping.RapidPrototypeApplication
import timber.log.Timber

class NewApp :RapidPrototypeApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.d("test")
    }
}