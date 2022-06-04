package br.com.poccompose.real.util

//import br.com.poccompose.application.PocComposeApplication
import java.util.*

object LocalUtil {
    fun getString(id: Int) = " "//getAppContext().getString(id)
    fun getLanguage() : String {
        return Locale.getDefault().language
    }
    fun getAppContext() = null//PocComposeApplication()
}