package br.com.poccompose.real.util

import androidx.annotation.StringRes
import br.com.poccompose.application.PocComposeApplication

object LocalUtil {
    fun getString(@StringRes id: Int) = PocComposeApplication.getString(id)
}