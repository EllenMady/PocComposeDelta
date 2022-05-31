package br.com.poccompose.application

import android.app.Application
import androidx.annotation.StringRes

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PocComposeApplication : Application() {
    companion object{
        private fun getInstance() = PocComposeApplication()
        fun getString(@StringRes id: Int){
            getInstance().resources.getString(id)
        }
    }
}