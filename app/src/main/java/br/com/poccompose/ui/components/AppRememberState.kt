package br.com.poccompose.ui.components

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.poccompose.R
import br.com.poccompose.ui.components.navigation.NavMainBottomRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
    ----------------------------------------------------------------------------------------------------------
    NavController é a API central do componente Navigation. É uma função com estado que acompanha a
    pilha de funções que podem ser compostas, as quais, por sua vez, criam as telas do app e o estado de cada tela.
    É possível criar um NavController usando o método rememberNavController() na função que pode ser composta
    ----------------------------------------------------------------------------------------------------------
     */
@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    titleState: MutableState<Int> = remember {
        mutableStateOf(R.string.client_register_title)
    },
    topActions: MutableState<List<TopBarAction>> = remember {
        mutableStateOf(listOf())
    }
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope, titleState, topActions) {
        AppState(scaffoldState, navController, snackbarManager, resources, coroutineScope,titleState, topActions)
    }




@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Stable
class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackbarManager: SnackbarManager,
    val resources: Resources,
    coroutineScope: CoroutineScope,
    val titleState: MutableState<Int>,
    val topActions: MutableState<List<TopBarAction>>,
) {
    // Process snackbars coming from SnackbarManager
    init {
        coroutineScope.launch {
            snackbarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getText(message.messageId)

                    // Display the snackbar on the screen. `showSnackbar` is a function
                    // that suspends until the snackbar disappears from the screen
                    scaffoldState.snackbarHostState.showSnackbar(text.toString())
                    // Once the snackbar is gone or dismissed, notify the SnackbarManager
                    snackbarManager.setMessageShown(message.id)
                }
            }
        }
    }

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------
    //val bottomBarTabs = HomeSections.values()
    private val bottomBarRoutes = listOf(
        NavMainBottomRoutes.Products.getRoute(),
        NavMainBottomRoutes.Sales.getRoute(),
        NavMainBottomRoutes.Reports.getRoute(),
        NavMainBottomRoutes.Menu.getRoute(),
        NavMainBottomRoutes.Clients.getRoute()
    )

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
    @Composable get() = isInMainDestination()

    @Composable
    fun getCurrentDestination() : String{
        val dest = navController.currentBackStackEntryAsState()
            .value?.
            destination?.route
        return dest ?: NavMainBottomRoutes.Products.getRoute()
    }

    @Composable
    fun isInMainDestination() : Boolean{
        val currentDestination = getCurrentDestination()
        return currentDestination in bottomBarRoutes
    }
    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun backPress() {
        navController.navigateUp()
    }

}