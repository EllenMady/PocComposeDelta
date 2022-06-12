package br.com.poccompose.ui.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.poccompose.R
import br.com.poccompose.ui.components.AppMenuSession
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.MenuItemObject
import br.com.poccompose.ui.components.MenuSessionObject


@Composable
fun MenuScreen(
    appState: AppState,
    modifier: Modifier = Modifier
){
    Box(
        modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            for(session in getSessionList()){
                AppMenuSession(
                    appState = appState, session= session )
            }

        }
    }


}

@Composable
private fun getSessionList(): List<MenuSessionObject>{
    val sessionZero = MenuSessionObject(
        items = listOf(
            MenuItemObject(
                title = "Item menu title Zero",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            ),
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            ),
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            )
        )
    )

    val sessionOne = MenuSessionObject(
        title = "Session title One",
        subTitle = "Session subtitle",
        items = listOf(
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            ),
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            ),
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            )
        )
    )
    val sessionTwo = MenuSessionObject(
        title = "Session title Two",
        subTitle = "Session subtitle",
        items = listOf(
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            )
        )
    )
    val sessionThree = MenuSessionObject(
        title = "Session title three",
        subTitle = "Session subtitle",
        items = listOf(
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            ),
            MenuItemObject(
                title = "Item menu title",
                subTitle = "Some thing to explain the item",
                icon = R.drawable.ic_settings_png
            )
        )
    )
    val sessionFour = MenuSessionObject(
        title = "Session Four",
        items = listOf(
            MenuItemObject(
                title = "Item menu title",
                icon = R.drawable.ic_settings_png
            ),
            MenuItemObject(
                title = "Item menu title",
                icon = R.drawable.ic_settings_png
            )
        )
    )
    return listOf(
        sessionZero,
        sessionOne,
        sessionTwo,
        sessionThree,
        sessionFour
    )
}