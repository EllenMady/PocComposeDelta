package br.com.poccompose.ui.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.poccompose.ui.components.*
import br.com.poccompose.ui.components.navigation.NavBarOptions


@Composable
fun AppMainScreen(){

//    val navController = rememberNavController()
    /*
    OBS @ ->
    Na hierarquia de funções que podem ser compostas, crie o NavController em um local onde todas as funções que
    precisem referenciá-lo tenham acesso a ele. Isso está de acordo com os princípios da elevação de estado e
    permite que você use o NavController e o estado apresentado por ele por currentBackStackEntryAsState(),
    para que essa função seja usada como a fonte da verdade para atualizar funções que podem ser compostas fora das telas.
     */
    //--------------------------------------------------------------------------------------------------------
    val bottomBar = remember {
        mutableStateOf<NavBarOptions>(NavBarOptions.Products)
    }
    val appState = rememberAppState()
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            AppTopBar(
                titleRes = appState.titleState.value,
                showBackButton = !appState.shouldShowBottomBar,
                backPress = appState::backPress,
                actions = appState.topActions.value,
                appState = appState
            )
        },
        bottomBar = {
            //---------------------------------
            //Her is the navigation bottom bar
            //---------------------------------
            val show = appState.shouldShowBottomBar
            if(show) {
                navBackStackEntry?.let {
                    AppBottomBar(
                        navController = appState.navController,
                        navBackStackEntry = it
                    )
                }
            }
        },
        floatingActionButton = {
            //If you nee to put a float bottom
        },
        snackbarHost = {
            //If you need to show a snack bar
        }
    ) { innerPadding ->
        AppNavHost(
            appState,
            navHostController = appState.navController,
            paddingValues = innerPadding)
    }
}