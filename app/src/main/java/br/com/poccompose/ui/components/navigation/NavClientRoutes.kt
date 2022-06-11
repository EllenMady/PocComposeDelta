package br.com.poccompose.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import br.com.poccompose.R
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.TopBarAction
import br.com.poccompose.ui.screen.client.ClientDetail
import br.com.poccompose.viewmodels.ClientDetailViewModel
import br.com.poccompose.viewmodels.ClientViewModel
import kotlin.reflect.KClass

sealed class NavClientRoutes(

) : Routable{
//    object ClientRegister : NavClientRoutes(
//
//    ) {
//        override fun getTitle(): Int {
//            return  R.string.client_register_title
//        }
//
//        override fun getRoute(): String {
//           return "${MainDestinations.CLIENT_ROUTE}/register"
//        }
//    }

    object ClientDetail : NavClientRoutes(
    ) {
        override fun getTitle(): Int {
            return R.string.client_register_title
        }

        override fun getRoute(): String {
            return "${MainDestinations.CLIENT_ROUTE}/detail"
        }

        @Composable override fun GetContent(appState: AppState) {
            ClientDetail()
        }

        @Composable
        override fun getActions(appState: AppState): List<TopBarAction> {
            val viewModel : ClientDetailViewModel = hiltViewModel()
            return listOf(
                TopBarAction(
                    icon = R.drawable.ic_notification_24
                ){
                    viewModel.invoke()
                }
            )
        }


    }
}
