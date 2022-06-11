package br.com.poccompose.ui.components.navigation

import androidx.compose.runtime.Composable
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.TopBarAction


interface Routable{
    fun getTitle() : Int
    fun getRoute() : String
    @Composable
    fun GetContent(appState: AppState)
    @Composable
    fun getActions(appState: AppState): List<TopBarAction>

}