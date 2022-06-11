package br.com.poccompose.ui.components.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.addRoute
import br.com.poccompose.ui.screen.*
import br.com.poccompose.viewmodels.ClientViewModel

/*
    Main routes for Navigation Bottom
 */
fun NavGraphBuilder.addMainNavigationGraph(
    appState: AppState
){
//    composable(
//        route = NavBarOptions.Products.getRoute()
//    ){
//        //Product Screen stuff
//        appState.titleState.value = NavBarOptions.Products.getTitle()
//        ProductsScreen()
//    }
    addRoute(appState,NavBarOptions.Products)
//    composable(
//        route = NavBarOptions.Sales.getRoute()
//    ){
//        // Sales Screen here
//        appState.titleState.value = NavBarOptions.Sales.getTitle()
//        SalesScreen()
//    }
    addRoute(appState,NavBarOptions.Sales)
//    composable(
//        route = NavBarOptions.Reports.getRoute()
//    ){
//        appState.titleState.value = NavBarOptions.Reports.getTitle()
//        ReportsScreen()
//    }
    addRoute(appState,NavBarOptions.Reports)
//    composable(
//        route = NavBarOptions.Menu.getRoute()
//    ){
//        //Menu Here
//        MenuScreen()
//    }
    addRoute(appState,NavBarOptions.Menu)
//    composable(
//        route = NavBarOptions.Clients.getRoute()
//    ){
//        // Creates a ViewModel from the current BackStackEntry
//        // Available in the androidx.hilt:hilt-navigation-compose artifact
//        val viewModel = hiltViewModel<ClientViewModel>()
//        ClientScreen(viewModel = viewModel,appState)
//    }
    addRoute(appState,NavBarOptions.Clients)
}