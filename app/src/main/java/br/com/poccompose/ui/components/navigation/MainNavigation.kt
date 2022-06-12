package br.com.poccompose.ui.components.navigation

import androidx.navigation.NavGraphBuilder
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.addRoute

/*
    Main routes for Navigation Bottom
 */
fun NavGraphBuilder.addMainNavigationGraph(
    appState: AppState
){
    addRoute(appState,NavMainBottomRoutes.Products)
    addRoute(appState,NavMainBottomRoutes.Sales)
    addRoute(appState,NavMainBottomRoutes.Reports)
    addRoute(appState,NavMainBottomRoutes.Menu)
    addRoute(appState,NavMainBottomRoutes.Clients)
}