package br.com.poccompose.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.poccompose.R
import br.com.poccompose.ui.components.AlertError


@Composable
fun ReportsScreen(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Box(modifier.fillMaxSize()){
        Column {
            val openDialog = remember { mutableStateOf(false)  }

            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Click me")
            }

            if(1 == 1 ) {
                AlertError(
                    openDialog = openDialog,
                    context = context,
                    idResMessage = R.string.you_need_active_subscription
                )
            }
            if(2 == 2 ){
                AlertError(
                    openDialog = openDialog,
                    context = context,
                    idResMessage = R.string.you_need_active_subscription
                )
            }
        }
    }
}