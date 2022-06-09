package br.com.poccompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.poccompose.R
import br.com.poccompose.real.util.ImageUtil
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter


@Composable
fun ImageFromUrl(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = url,
        placeholder = BitmapPainter(image = ImageUtil.getImageFromResources(context.resources,R.drawable.ic_wait_placeholder).asImageBitmap()),
        error = BitmapPainter(image = ImageUtil.getImageFromResources(context.resources,R.drawable.ic_no_image).asImageBitmap()),
        imageLoader = ImageLoader(context))
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.clip(RoundedCornerShape(15.dp))
    )
}