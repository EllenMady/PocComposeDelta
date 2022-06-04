package br.com.poccompose.application

import android.app.Application
import androidx.annotation.StringRes

import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
open class App : Application() {
    companion object{
        fun getInstance() = App()
        fun getString(@StringRes id: Int): String{
            return getInstance().resources.getString(id)
        }
    }
}