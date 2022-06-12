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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.poccompose.real.util.ImageUtil
import br.com.poccompose.ui.components.objects.CardImageItem
import br.com.poccompose.ui.components.objects.CardImageItemProduct


@Composable
fun AppImageCardItem(
    appState: AppState,
    cardImageItem: CardImageItem
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .height(70.dp)
            .clickable { },
        elevation = 3.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            Image(
                bitmap = ImageUtil.getImageNoImageProduct(appState.resources).asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .padding(8.dp)
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    Text(
                        text = cardImageItem.getTitle(),
                        modifier = Modifier.padding(top = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = cardImageItem.getSubtitle(),
                        modifier = Modifier
                            .padding(top = 12.dp),
                        fontSize = 12.sp
                    )
                }
                Text(
                    text = cardImageItem.getLeftBottomInfo(),
                    color = cardImageItem.getLeftBottomInfoColor(),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 3.dp, end = 4.dp)

                )
            }
        }
    }
}

@Preview
@Composable
fun Preview(){
    AppImageCardItem(appState = rememberAppState(),CardImageItemProduct(
        "iPhone 7 plus",
        "48",
        "R$ 6.145,00"
    ))
}