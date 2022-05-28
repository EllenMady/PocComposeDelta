package br.com.poccompose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import br.com.poccompose.ui.components.navigation.MainDestinations

sealed class NavBarOptions(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object Products : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/products",
        title = "Produtos",
        icon = Icons.Default.Phone
    )

    object Sales : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/sales",
        title = "Vendas",
        icon = Icons.Default.ShoppingCart
    )

    object Clients : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/clients",
        title = "Clientes",
        icon = Icons.Default.Person
    )

    object Reports : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/reports",
        title = "Relatórios",
        icon = Icons.Default.DateRange
    )

    object Menu : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/menu",
        title = "Menu",
        icon = Icons.Default.Menu
    )
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


