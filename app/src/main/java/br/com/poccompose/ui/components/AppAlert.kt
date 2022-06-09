package br.com.poccompose.ui.components

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.poccompose.R
import br.com.poccompose.real.util.ImageUtil


@Composable
fun AlertError(
    context: Context,
    @StringRes idResMessage: Int,
    openDialog : MutableState<Boolean>,
    onConfirm: () -> Unit = {}

){
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                openDialog.value = false
            },
            title = {
                Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier.fillMaxWidth()) {
                    Image(
                        bitmap = ImageUtil.getImageFromResources(context.resources, R.drawable.ic_no).asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Text(text = stringResource(id = R.string.warning))
                }

            },
            text = {
                Text(stringResource(id = idResMessage))
            },
            confirmButton = {
                Button(

                    onClick = {
                        openDialog.value = false
                        onConfirm.invoke()
                    }) {
                    Text("This is the Confirm Button")
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("This is the dismiss Button")
                }
            }
        )
    }

}