package br.com.poccompose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.poccompose.R
import br.com.poccompose.ui.components.navigation.MainDestinations

sealed class NavBarOptions(
    val route : String,
    val title : Int,
    val icon : ImageVector
){
    object Products : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/products",
        title = R.string.nav_bottom_products,
        icon = Icons.Default.Phone
    )

    object Sales : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/sales",
        title = R.string.nav_bottom_sales,
        icon = Icons.Default.ShoppingCart
    )

    object Clients : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/clients",
        title = R.string.nav_bottom_clients,
        icon = Icons.Default.Person
    )

    object Reports : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/reports",
        title = R.string.nav_bottom_reports,
        icon = Icons.Default.DateRange
    )

    object Menu : NavBarOptions(
        route = "${MainDestinations.MAIN_ROUTE}/menu",
        title = R.string.nav_bottom_menu,
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


