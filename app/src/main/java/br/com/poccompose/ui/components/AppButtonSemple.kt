package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun AppButtonSample(
    enabled: Boolean = true,
    text: String ,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Button(
        enabled = enabled,
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.secondary,
                disabledBackgroundColor = MaterialTheme.colors.secondaryVariant,
                disabledContentColor = MaterialTheme.colors.surface.copy(alpha = 0.12f)
            )
        ,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        onClick = onClick) {
        Text(text = text)
    }
}