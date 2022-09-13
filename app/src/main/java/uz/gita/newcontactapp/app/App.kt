package uz.gita.newcontactapp.app

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.newcontactapp.data.local.database.AppDatabase
import uz.gita.newcontactapp.data.local.shp.MySharedPreference


@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppDatabase.init(this)
        MySharedPreference.init(this)
        instance = this
    }
}