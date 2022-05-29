package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun AppMenuItem(
    text: String
){
   Button(
       onClick = { /*TODO*/ },
        Modifier.fillMaxWidth()
       ) {
       Icon(imageVector = Icons.Default.Person, contentDescription = null)
       Text(text = text)
   }
}

@Composable
fun ColumnScope.addMenuItem(){
    AppMenuItem("Option One")
}