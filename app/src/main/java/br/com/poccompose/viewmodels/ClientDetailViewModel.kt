package br.com.poccompose.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientDetailViewModel @Inject constructor() : ViewModel() {

    val isInvoked: MutableState<Boolean> = mutableStateOf(false)
    val funMessage: MutableState<String> = mutableStateOf("")
    fun invoke(){
        Log.d("INVOKE", "Yes its works!!!")
        isInvoked.value = true
    }

    fun showFunMessage(){
        funMessage.value = "That is funny!!"
    }

}