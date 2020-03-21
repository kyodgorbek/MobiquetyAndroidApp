package android.com.mobiquetyandroidapp

import android.app.Application
import android.com.mobiquetyandroidapp.di.appModule
import android.os.StrictMode
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
            System.setProperty("kotlinx.coroutines.debug", "on")
        }

        startKoin(this, appModule)
    }

}