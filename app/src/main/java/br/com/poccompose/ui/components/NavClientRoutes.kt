package br.com.poccompose.ui.components

import br.com.poccompose.R
import br.com.poccompose.ui.components.navigation.MainDestinations

sealed class NavClientRoutes(
    val route: String,
    val title : Int
){
    object ClientRegister : NavClientRoutes(
        route = "${MainDestinations.PRODUCT_ROUTE}/register",
        title = R.string.client_register_title
    )
}
