package br.com.poccompose.ui.screen.client

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.viewmodels.ClientDetailViewModel

@Composable
fun ClientDetail(
    clientDetailViewModel: ClientDetailViewModel = hiltViewModel()
){
    val context = LocalContext.current
    if(clientDetailViewModel.isInvoked.value){
        Toast.makeText(context,"Ok, its works fine", Toast.LENGTH_SHORT).show()
    }
    Column {
        Text(text = "Client Detail")
        Text(text = "Content goes here")
        Button(onClick = {
            clientDetailViewModel.showFunMessage()
        }) {
            Text(text = "Click here to see a fun message.")
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = clientDetailViewModel.funMessage.value)
    }
}