package br.com.poccompose.application

import android.app.Application
import androidx.annotation.StringRes
import br.com.poccompose.real.util.LocalNotificationUtil

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {

    //TODO to work the notifications, we need to create the channels at first app init
    override fun onCreate() {
        super.onCreate()
        LocalNotificationUtil(this).createNotificationChannel()
    }
    companion object{
        fun getInstance() = App()
        fun getString(@StringRes id: Int): String{
            return getInstance().resources.getString(id)
        }
    }
}