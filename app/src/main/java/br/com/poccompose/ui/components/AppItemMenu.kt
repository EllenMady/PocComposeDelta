package br.com.poccompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.poccompose.R
import br.com.poccompose.real.util.ImageUtil
import br.com.poccompose.ui.theme.backGroundColor
import br.com.poccompose.ui.theme.textContentColor
import br.com.poccompose.ui.theme.textContentSecondaryColor

@Composable
fun AppMenuItem(
    appState: AppState,
    item: MenuItemObject,
    showDivider: Boolean = false,
    roundTopCorners: Boolean = true,
    roundBottomCorners: Boolean = true,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
    ) {
        val buttonModifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
        val roundDp = 12.dp
        val resultModify = when{
            roundTopCorners && roundBottomCorners ->{
                buttonModifier.clip(RoundedCornerShape(roundDp))
            }
            roundTopCorners -> {
                buttonModifier.clip(RoundedCornerShape(topStart = roundDp, topEnd = roundDp))
            }
            roundBottomCorners -> {
                buttonModifier.clip(RoundedCornerShape(bottomStart = roundDp, bottomEnd = roundDp))
            }
            else -> buttonModifier.clip(RoundedCornerShape(0.dp))
        }

        Button(
            onClick = onClick,
            modifier = resultModify,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backGroundColor,
                contentColor = textContentColor
            )
        ) {
            Image(
                bitmap = ImageUtil.getImageFromResources(
                    appState.resources,
                    item.icon).asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .width(22.dp)
                    .height(22.dp)

            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)) {
                Text(
                    text = item.title,
                )
                if(item.subTitle.trim().isNotEmpty()) {
                    Text(
                        text = item.subTitle,
                        fontSize = 12.sp,
                        color = textContentSecondaryColor
                    )
                }
            }
        }
        if(showDivider) {
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(end = 12.dp, start = 55.dp),
                color = textContentSecondaryColor
            )
        }

    }

}

@Composable
@Preview
fun PreviewItem(){
    val item = MenuItemObject(
        title = "title",
        icon = R.drawable.ic_notification_24
    )
    Column {
        AppMenuItem(item = item, appState =  rememberAppState(), showDivider = true){

        }

        AppMenuItem(item = item, appState =  rememberAppState(), showDivider = true){

        }

        AppMenuItem(item = item, appState =  rememberAppState(), showDivider = true){

        }


    }

}