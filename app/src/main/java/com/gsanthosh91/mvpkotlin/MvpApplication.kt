package com.gsanthosh91.mvpkotlin

import android.app.Application
import android.content.ContextWrapper
import com.facebook.stetho.Stetho
import com.pixplicity.easyprefs.library.Prefs

class MvpApplication : Application() {

    companion object {
        lateinit var mInstance: MvpApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

}