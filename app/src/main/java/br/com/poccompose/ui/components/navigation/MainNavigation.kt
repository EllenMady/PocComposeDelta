package br.com.poccompose.ui.components.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.poccompose.ui.components.NavBarOptions
import br.com.poccompose.ui.screen.*
import br.com.poccompose.viewmodels.ClientViewModel

/*
    Main routes for Navigation Bottom
 */
fun NavGraphBuilder.addMainNavigationGraph(){
    composable(
        route = NavBarOptions.Products.route
    ){
        //Product Screen stuff
        ProductsScreen()
    }
    composable(
        route = NavBarOptions.Sales.route
    ){
        // Sales Screen here
        SalesScreen()
    }
    composable(
        route = NavBarOptions.Reports.route
    ){
        // Reports
        ReportsScreen()
    }
    composable(
        route = NavBarOptions.Menu.route
    ){
        //Menu Here
        MenuScreen()
    }
    composable(
        route = NavBarOptions.Clients.route
    ){
        // Creates a ViewModel from the current BackStackEntry
        // Available in the androidx.hilt:hilt-navigation-compose artifact
        val viewModel = hiltViewModel<ClientViewModel>()
        ClientScreen(viewModel = viewModel)
    }
}