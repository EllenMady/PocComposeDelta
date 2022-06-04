package br.com.poccompose.real.util

import br.com.poccompose.application.App
import java.util.*

object LocalUtil {
    fun getString(id: Int) = getAppContext().getString(id)
    fun getLanguage() : String {
        return Locale.getDefault().language
    }
    fun getAppContext() = App.getInstance()
}