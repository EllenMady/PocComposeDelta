package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.poccompose.R

@Composable
fun AppMenuSession(
    appState: AppState,
    session: MenuSessionObject,
    modifier: Modifier = Modifier
){
    Column {
        Spacer(modifier = Modifier.height(6.dp))
        if(session.title.trim().isNotEmpty()) {
            Text(
                text = session.title,
                fontFamily = FontFamily.SansSerif,
                color = Color.Gray
            )
        }else{
            Spacer(modifier = Modifier.height(2.dp))
        }
        if(session.subTitle.trim().isNotEmpty()) {
            Text(
                text = session.subTitle,
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if(session.items.size == 1){
            AppMenuItem(
                appState = appState,
                item = session.items[0]
            ) {

            }
        }else {
            CreateSessionForMoreItems(session, appState)
        }

    }
}

@Composable
private fun CreateSessionForMoreItems(
    session: MenuSessionObject,
    appState: AppState
) {
    session.items.forEachIndexed { index, menuItem ->
        when (index) {
            0 -> {
                AppMenuItem(
                    appState = appState,
                    item = menuItem,
                    roundBottomCorners = false,
                    showDivider = true
                ) {

                }
            }
            session.items.size - 1 -> {
                AppMenuItem(appState = appState, item = menuItem, roundTopCorners = false) {

                }
            }
            else -> {
                AppMenuItem(
                    appState = appState,
                    item = menuItem,
                    roundBottomCorners = false,
                    roundTopCorners = false,
                    showDivider = true
                ) {

                }
            }
        }

    }
}

@Composable
@Preview
fun PreviewMenuSession(){
    val session = MenuSessionObject(
        title = "Session title",
        subTitle = "Session subtitle",
        items = listOf(
            MenuItemObject(
                title = "Item menu title",
                icon = R.drawable.ic_notification_24
            )
        )
    )
    AppMenuSession(
        appState = rememberAppState(), session= session )
}