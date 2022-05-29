package br.com.poccompose.ui.components.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.poccompose.ui.components.NavBarOptions
import br.com.poccompose.ui.screen.*
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
        ClientScreen()
    }
}