package br.com.poccompose.ui.components.navigation

import androidx.navigation.NavGraphBuilder
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.addRoute

fun NavGraphBuilder.addClientNavigation(
    appState: AppState
){
    addRoute(appState, NavClientRoutes.ClientDetail)
}
