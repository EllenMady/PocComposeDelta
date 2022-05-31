package br.com.poccompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.poccompose.ui.components.AppMenuItem


@Composable
fun MenuScreen(
    modifier: Modifier = Modifier
){
    Box(modifier
        .fillMaxSize()
        .padding(start = 8.dp, end = 8.dp)){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Text(text = "Menu")
            AppMenuItem(text = "Option One")
            AppMenuItem(text = "Option Two")
            AppMenuItem(text = "Option Three")
        }
    }
}