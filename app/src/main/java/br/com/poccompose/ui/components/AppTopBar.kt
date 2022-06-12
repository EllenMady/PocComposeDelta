package br.com.poccompose.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.poccompose.real.enums.IconTypeEnum
import br.com.poccompose.real.util.ImageUtil
import br.com.poccompose.ui.components.objects.TopBarAction


@Composable
fun AppTopBar(
    titleRes: Int,
    showBackButton: Boolean = true,
    modifier: Modifier = Modifier,
    backPress: () -> Unit,
    actions: List<TopBarAction>,
    appState: AppState
){
    TopAppBar(
        navigationIcon = {
            if(showBackButton) {
                IconButton(onClick = backPress) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        title = {
            Text(
                text = stringResource(id = titleRes),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )
        },
        modifier = modifier,
        actions = {
            for(action in actions){
                IconButton(
                    onClick = action.onClick,
                    enabled = true
                ){
                    when(action.iconType){
                         IconTypeEnum.IMAGE -> {
                             Icon(
                                 bitmap = ImageUtil
                                     .getImageFromResources(appState.resources,action.icon)
                                     .asImageBitmap(),
                                 contentDescription = null
                             )
                         }
                        else -> {
                            Icon(
                                painter = painterResource(id = action.icon),
                                contentDescription = null
                            )
                        }
                    }

                }
            }
        },
        backgroundColor = Color.Black.copy(0.06f)
    )
}

