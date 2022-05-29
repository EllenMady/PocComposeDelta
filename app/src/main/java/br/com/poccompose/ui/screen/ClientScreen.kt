package br.com.poccompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.poccompose.ui.components.AppButtonSample


@Composable
fun ClientScreen(
    modifier: Modifier = Modifier
){
    var count by remember {
        mutableStateOf(0)
    }
    Box(modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Clientes $count")
            AppButtonSample(text = "Enable"){
                count++
            }
            AppButtonSample(text = "Disabled", enabled = false){
                count++
            }

            Button(onClick = { count++ }) {
                Text(text = "Click here")
            }
            Button(onClick = { count++ },enabled = if(count > 10) false else true) {
                Text(text = "Click here")
            }
        }
    }
}

@Preview
@Composable
fun ClientsPreview(){
    ClientScreen()
}