package br.com.poccompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import br.com.poccompose.real.util.ImageUtil


@Composable
fun AppImageCardItem(
    appState: AppState,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(80.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                bitmap = ImageUtil.getImageNoImageProduct(appState.resources).asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .padding(8.dp)
            )
            Column {
                Text(text = "Text one")
                Text(text = "Text Two")
            }
        }
    }
}