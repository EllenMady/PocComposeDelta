package br.com.poccompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.poccompose.model.entities.ClientEntity
import br.com.poccompose.real.util.PreferencesUtil
import br.com.poccompose.repositories.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val clientRepository: ClientRepository
) : ViewModel() {

    fun testPreferences(){
        viewModelScope.launch {
            //PreferencesUtil().setString("test-key", "value")
        }
    }

    fun getPrefExample() : String {
        return ""//PreferencesUtil().getString("test-key")
    }
}