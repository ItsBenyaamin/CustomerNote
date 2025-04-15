package com.benyaamin.customernote

import android.app.Application
import com.benyaamin.customernote.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CustomerNoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomerNoteApplication)
            modules(appModule)
        }
    }
}