package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import br.com.poccompose.ui.components.navigation.MainDestinations


@Composable
fun AppBottomBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier
){
    val currentDestination = navBackStackEntry.destination
    BottomNavigation(
        elevation = 3.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        NavBarOptionObject.getNavBarOption().forEach { navBarOptions ->
            AddItem(
                barOption = navBarOptions,
                currentDestination = currentDestination,
                navHostController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    barOption: NavBarOptions,
    currentDestination: NavDestination,
    navHostController: NavHostController
){
    BottomNavigationItem(
        label = {
                Text(text = barOption.title)
        },
        selected = currentDestination.hierarchy.any{ navDestination ->
           navDestination.route == barOption.route
        },
        icon = {
               Icon(imageVector = barOption.icon, contentDescription = barOption.title)
        },
        onClick = {
            /*
            No gráfico de navegação, use o método navigate() para navegar até um destino
            que pode ser composto. navigate() usa um único parâmetro String que representa
            a rota do destino. Para navegar de um destino dentro do gráfico de navegação,
            chame navigate():
             */
            navHostController.navigate(barOption.route){
                launchSingleTop = true
                /*
                popUpTo MAIN_ROUTE faz com que em qualquer item de menu, o mesmo fecha o app ao clicar no botao de back
                do aparelho
                 */
                popUpTo(route = MainDestinations.MAIN_ROUTE)
            }
        }
    )
}