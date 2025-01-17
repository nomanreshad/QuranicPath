package com.example.quranicpath

import android.app.Application
import com.example.quranicpath.di.initKoin
import org.koin.android.ext.koin.androidContext

class QuranApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@QuranApplication)
        }
    }
}