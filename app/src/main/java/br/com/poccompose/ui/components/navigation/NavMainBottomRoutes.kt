package br.com.poccompose.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.poccompose.R
import br.com.poccompose.real.enums.IconTypeEnum
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.objects.TopBarAction
import br.com.poccompose.ui.screen.*
import br.com.poccompose.viewmodels.ProductsViewModel

sealed class NavMainBottomRoutes(
    val icon : Int
): Routable{
    object Products : NavMainBottomRoutes(
        icon = R.drawable.ic_product_menu
    ) {
        override fun getTitle()= R.string.nav_bottom_products

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/products"

        @Composable override fun GetContent(appState: AppState) {
            ProductsScreen(appState)
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            val viewModel: ProductsViewModel = hiltViewModel()
            val iconType = IconTypeEnum.VECTOR
            return listOf(
                TopBarAction(
                    icon = R.drawable.ic_search,
                    iconType = iconType
                ){
                    //Call ViewModel action or navigation
                },
                TopBarAction(
                    icon = R.drawable.ic_add,
                    iconType = iconType
                ){

                },
                TopBarAction(
                    icon = R.drawable.ic_vertical_menu,
                    iconType = iconType
                ){

                }
            )
        }

    }

    object Sales : NavMainBottomRoutes(
        icon = R.drawable.ic_cart_shop
    ) {
        override fun getTitle()= R.string.nav_bottom_sales

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/sales"

        @Composable override fun GetContent(appState: AppState) {
            SalesScreen()
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            val iconType = IconTypeEnum.VECTOR
            return listOf(
                TopBarAction(
                    icon = R.drawable.ic_search,
                    iconType = iconType
                ){
                    //Call ViewModel action or navigation
                },
                TopBarAction(
                    icon = R.drawable.ic_add,
                    iconType = iconType
                ){

                },
                TopBarAction(
                    icon = R.drawable.ic_vertical_menu,
                    iconType = iconType
                ){

                }
            )
        }


    }

    object Clients : NavMainBottomRoutes(
        icon = R.drawable.ic_client_menu
    ) {
        override fun getTitle()= R.string.nav_bottom_clients

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/clients"

        @Composable override fun GetContent(appState: AppState) {
            ClientScreen(appState= appState)
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            val iconType = IconTypeEnum.VECTOR
            return listOf(
                TopBarAction(
                    icon = R.drawable.ic_download,
                    iconType = iconType
                ){
                    //Call ViewModel action or navigation
                },
                TopBarAction(
                    icon = R.drawable.ic_add,
                    iconType = iconType
                ){

                },
                TopBarAction(
                    icon = R.drawable.ic_vertical_menu,
                    iconType = iconType
                ){

                }
            )
        }


    }

    object Reports : NavMainBottomRoutes(
        icon = R.drawable.ic_report_menu
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

    object Menu : NavMainBottomRoutes(
        icon = R.drawable.ic_menu
    ) {
        override fun getTitle()= R.string.nav_bottom_menu

        override fun getRoute()= "${MainDestinations.MAIN_ROUTE}/menu"

        @Composable override fun GetContent(appState: AppState) {
            MenuScreen(appState = appState)
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            return listOf()
        }
    }
}

object NavBarOptionObject {
    fun getNavBarOption() = listOf(
        NavMainBottomRoutes.Products,
        NavMainBottomRoutes.Sales,
        NavMainBottomRoutes.Clients,
        NavMainBottomRoutes.Reports,
        NavMainBottomRoutes.Menu
    )
}