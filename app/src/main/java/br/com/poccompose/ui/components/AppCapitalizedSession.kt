package br.com.poccompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.poccompose.ui.components.objects.CardImageItemProduct

@Composable
fun AppCapitalizedSession(
    capital: String,
    content: @Composable ColumnScope.() -> Unit
){
    Column(modifier = Modifier
        .padding(end = 2.dp)
        .fillMaxWidth()
    ) {
        Text(
            text = capital,
            modifier = Modifier.padding(start = 12.dp, top = 4.dp)
        )
        Column(
            content = content,
            modifier = Modifier.padding(start = 12.dp, end = 6.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLayout(){
    Column {
        Text(text = "SDD")
        AppCapitalizedSession(capital = "A") {
            AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                "iPhone 7 plus",
                "48",
                "R$ 6.145,00"
            )
            )
            AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                "iPhone 7 plus live",
                "110",
                "R$ 4.145,00"
            )
            )
        }
        Text(text = "SDD")
        AppCapitalizedSession(capital = "B") {
            AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                "iPhone 7 plus",
                "48",
                "R$ 6.145,00"
            )
            )
            AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                "iPhone 7 plus live",
                "110",
                "R$ 4.145,00"
            )
            )
        }
    }

}