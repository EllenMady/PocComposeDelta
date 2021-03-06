package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import br.com.poccompose.ui.components.navigation.MainDestinations
import br.com.poccompose.ui.components.navigation.NavBarOptionObject
import br.com.poccompose.ui.components.navigation.NavMainBottomRoutes
import br.com.poccompose.ui.theme.*


@Composable
fun AppBottomBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier
){
    val currentDestination = navBackStackEntry.destination
    BottomNavigation(
        backgroundColor = backGroundColor,
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
    barOption: NavMainBottomRoutes,
    currentDestination: NavDestination,
    navHostController: NavHostController
){

    val selectedColor = when(barOption.getRoute()){
        NavMainBottomRoutes.Clients.getRoute() -> ClientsColor
        NavMainBottomRoutes.Sales.getRoute() -> SalesColor
        NavMainBottomRoutes.Products.getRoute() -> ProductColor
        NavMainBottomRoutes.Reports.getRoute() -> ReportsColor
        NavMainBottomRoutes.Menu.getRoute() -> MenuColor
        else -> MaterialTheme.colors.primary
    }
    BottomNavigationItem(
        label = {
                Text(
                    text = stringResource(id = barOption.getTitle()),
                    fontSize = 10.sp
                )
        },
        selected = currentDestination.hierarchy.any{ navDestination ->
           navDestination.route == barOption.getRoute()
        },
        icon = {
               Icon(painter = painterResource(id = barOption.icon) , contentDescription = stringResource(id = barOption.getTitle()))
        },
        selectedContentColor = selectedColor,
        unselectedContentColor = selectedColor.copy(0.2f)
        ,
        onClick = {
            /*
            No gr??fico de navega????o, use o m??todo navigate() para navegar at?? um destino
            que pode ser composto. navigate() usa um ??nico par??metro String que representa
            a rota do destino. Para navegar de um destino dentro do gr??fico de navega????o,
            chame navigate():
             */
            navHostController.navigate(barOption.getRoute()){
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