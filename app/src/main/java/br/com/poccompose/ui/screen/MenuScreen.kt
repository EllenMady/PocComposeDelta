package br.com.poccompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.poccompose.ui.components.AppMenuItem


@Composable
fun MenuScreen(
    modifier: Modifier = Modifier
){
    Box(modifier.fillMaxSize()){
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