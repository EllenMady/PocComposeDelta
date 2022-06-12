package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.poccompose.ui.components.navigation.*


@Composable
fun AppNavHost(
    appState: AppState,
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    modifier : Modifier = Modifier
){
    /*
        @ - NavHost: é um contêiner vazio que mostra destinos do gráfico de navegação.
        O componente de navegação contém uma implementação NavHostpadrão, NavHostFragment, que mostra os destinos do fragmento
        @ - NavController: é um objeto que gerencia a navegação do aplicativo em um NavHost. O NavController organiza a troca do
        conteúdo de destino no NavHost conforme os usuários se movem pelo aplicativo.
     */
    /*
    Cada NavController precisa ser associado a um único NavHost que pode ser composto. NavHost vincula NavController a um gráfico
    de navegação que especifica entre quais destinos que podem ser compostos você pode navegar. À medida que você navega entre as
    funções que podem ser compostas, o conteúdo do NavHost é automaticamente recomposto. Cada destino composto no gráfico de navegação
    está associado a uma rota.
     */
    NavHost(
        navController = navHostController,
        /*
        Observação: o componente Navigation requer que os Princípios do Navigation sejam seguidos e que um destino inicial
        fixo seja usado. Para a rota startDestination, não use um valor que pode ser composto.
         */
        startDestination = MainDestinations.MAIN_ROUTE,
        modifier = modifier.padding(paddingValues = paddingValues)
    ){
        /*
        Termo-chave: rota é uma String que define o caminho para a função que pode ser composta.
        Pense nela como um link direto implícito que leva a um destino específico. Cada destino
        precisa ter uma rota exclusiva.
         */
        navigation(
            startDestination = NavMainBottomRoutes.Products.getRoute(),
            route = MainDestinations.MAIN_ROUTE
        ){
            addMainNavigationGraph(appState)
            addClientNavigation(appState)
        }
    }
}

fun NavGraphBuilder.addRoute(appState: AppState, routable: Routable){
    composable(
        route= routable.getRoute(),
    ){
        appState.topActions.value = routable.getActions(appState)
        appState.titleState.value = routable.getTitle()
        routable.GetContent(appState)
    }
}

