package br.com.poccompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.poccompose.real.util.ImageUtil
import br.com.poccompose.real.util.PreferencesUtil
import kotlin.coroutines.coroutineContext


@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Box(modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Produtos")
            Button(onClick = {
                //PreferencesUtil().setString("value","key")
                PreferencesUtil.setString(context,"Sherede test","any-key")
            }) {
                Text(text = "Test Preferences")
            }
            Text(text = PreferencesUtil.getString(context,"any-key"))

            Image(
                bitmap = ImageUtil.getImageNoImageProduct(context.resources).asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.width(128.dp).height(128.dp)
                )
        }
    }
}