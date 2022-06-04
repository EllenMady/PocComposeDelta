package br.com.poccompose.real.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import br.com.poccompose.application.App
import kotlinx.coroutines.flow.map

const val PREFERENCE_NAME = "preferences"

object PreferencesUtil {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    suspend fun setStringAsStore(context: Context, value: String, key: String){
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[prefKey] = value
        }
    }

    fun getStringAsStore(key: String) : String{
        val prefKey = stringPreferencesKey(key)
        return App.getInstance().dataStore.data.map {
            it[prefKey]
        }.toString()
    }

    fun getString(context: Context, key: String, defaultValue: String = "") : String{
        val pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return pref.getString(key,defaultValue).orEmpty()
    }

    fun setString(context: Context, value: String, key: String){
        val pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        pref.edit {
            putString(key,value)
            apply()
        }
    }





}