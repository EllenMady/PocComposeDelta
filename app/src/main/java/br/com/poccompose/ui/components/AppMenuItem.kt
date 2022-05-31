package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


@Composable
fun AppMenuItem(
    text: String
){
   Button(
       onClick = { /*TODO*/ },
        Modifier.fillMaxWidth()
       ) {
       Box{
           Icon(imageVector = Icons.Default.Person, contentDescription = null)
           Text(
               text = text,
                modifier = Modifier.fillMaxWidth(),
               textAlign = TextAlign.Center
           )
           Text(
               text = ">",
               textAlign = TextAlign.End,
               modifier = Modifier.fillMaxWidth()
           )
       }
   }
}

@Composable
fun ColumnScope.addMenuItem(){
    AppMenuItem("Option One")
}