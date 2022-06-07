package br.com.poccompose.ui.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.poccompose.ui.components.AppBottomBar
import br.com.poccompose.ui.components.AppNavHost
import br.com.poccompose.ui.components.NavBarOptions


@Composable
fun AppMainScreen(){
    /*
    ----------------------------------------------------------------------------------------------------------
    NavController é a API central do componente Navigation. É uma função com estado que acompanha a
    pilha de funções que podem ser compostas, as quais, por sua vez, criam as telas do app e o estado de cada tela.
    É possível criar um NavController usando o método rememberNavController() na função que pode ser composta
    ----------------------------------------------------------------------------------------------------------
     */
    val navController = rememberNavController()
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
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            //Here comes the app top bar
        },
        bottomBar = {
            //Her is the navigation bottom bar
            navBackStackEntry?.let { AppBottomBar(navController = navController, navBackStackEntry = it) }
        },
        floatingActionButton = {
            //If you nee to put a float bottom
        },
        snackbarHost = {
            //If you need to show a snack bar
        }
    ) { innerPadding ->
        AppNavHost(
            navHostController = navController,
            paddingValues = innerPadding)
    }
}