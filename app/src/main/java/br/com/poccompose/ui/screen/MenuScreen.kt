package br.com.poccompose.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.poccompose.real.util.EmailDetail
import br.com.poccompose.real.util.EmailUtil
import br.com.poccompose.real.util.WhatsAppUtil
import br.com.poccompose.ui.components.AppMenuItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val bottomSheetModalState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    Box(modifier
        .fillMaxSize()
        .padding(start = 8.dp, end = 8.dp)){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Text(text = "Menu")
            AppMenuItem(text = "Option One - Send E-mail"){
                val emailIntent = EmailUtil.getIntent(EmailDetail(
                    recipient = "bruno_salmito@hotmail.com","Assunto do email","Corpo do email"
                ))
                /*
                O Intent.createChooser é mais usado para compartilhar
                 */
                val shareIntent = Intent.createChooser(emailIntent,"title")
                //context.startActivity(shareIntent)
                /*
                Esse abaixo é melhor para mandar só e-mail
                 */
                context.startActivity(emailIntent)
            }
            AppMenuItem(text = "Option Two"){

                if(WhatsAppUtil.isDeviceWith(packageManager = context.packageManager)) {
                    val whatsAppIntent = WhatsAppUtil.getWhatsAppIntent("Message OK mano")
                    context.startActivity(whatsAppIntent)
                }else{
                    //No zap here
                    val valdd = ""
                }
            }
            AppMenuItem(text = "Picture"){
                coroutineScope.launch {
                    if(bottomSheetModalState.isVisible){
                        bottomSheetModalState.hide()
                    }else{
                        bottomSheetModalState.show()
                    }
                }
            }
        }
    }
}