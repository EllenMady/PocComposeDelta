package br.com.poccompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.poccompose.ui.components.AppButtonSample
import br.com.poccompose.viewmodels.ClientViewModel


@Composable
fun ClientScreen(
    viewModel: ClientViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    /*
    No compose, um estado é tudo que pode mudar em uma tela
     */
    /*
    Use o remember caso voce queira que o estado persista durante as recomposicoes
     */
    var countNoSave by remember {
        mutableStateOf(0)
    }
    /*
    Caso voce queira que o estado persista mesmo que a tela mude de posicao (paisagem e retrato),
    devera usar o rememberSaveable, que ira persistir a info mesmo com a reconstrucao da tela
     */
    var countSave by rememberSaveable {
        mutableStateOf(0)
    }
    Box(modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Clientes no save $countNoSave")
            Text(text = "Clientes with save $countSave")
            AppButtonSample(text = "Enable"){
                countNoSave++
                countSave++
            }
            AppButtonSample(text = "Disabled", enabled = false){
                countNoSave++
                countSave++
            }

            Button(onClick = { countNoSave++;countSave++ }) {
                Text(text = "Click here")
            }
            Button(onClick = { countNoSave++;countSave++ },enabled = if(countNoSave > 10) false else true) {
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