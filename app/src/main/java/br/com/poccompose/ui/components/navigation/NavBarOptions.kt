package br.com.poccompose.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.poccompose.R
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.TopBarAction
import br.com.poccompose.ui.screen.*

sealed class NavBarOptions(
    val icon : ImageVector
): Routable{
    object Products : NavBarOptions(
        icon = Icons.Default.Phone
    ) {
        override fun getTitle()= R.string.nav_bottom_products

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/products"

        @Composable override fun GetContent(appState: AppState) {
            ProductsScreen()
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            return listOf()
        }

    }

    object Sales : NavBarOptions(
        icon = Icons.Default.ShoppingCart
    ) {
        override fun getTitle()= R.string.nav_bottom_sales

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/sales"

        @Composable override fun GetContent(appState: AppState) {
            SalesScreen()
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            return listOf()
        }


    }

    object Clients : NavBarOptions(
        icon = Icons.Default.Person
    ) {
        override fun getTitle()= R.string.nav_bottom_clients

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/clients"

        @Composable override fun GetContent(appState: AppState) {
            ClientScreen(appState= appState)
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            return listOf()
        }


    }

    object Reports : NavBarOptions(
        icon = Icons.Default.DateRange
    ) {
        override fun getTitle()= R.string.nav_bottom_reports

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/reports"

        @Composable override fun GetContent(appState: AppState) {
            ReportsScreen()
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            return listOf()
        }


    }

    object Menu : NavBarOptions(
        icon = Icons.Default.Menu
    ) {
        override fun getTitle()= R.string.nav_bottom_menu

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/menu"

        @Composable override fun GetContent(appState: AppState) {
            MenuScreen()
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            return listOf()
        }
    }
}

object NavBarOptionObject {
    fun getNavBarOption() = listOf(
        NavBarOptions.Products,
        NavBarOptions.Sales,
        NavBarOptions.Clients,
        NavBarOptions.Reports,
        NavBarOptions.Menu
    )
}