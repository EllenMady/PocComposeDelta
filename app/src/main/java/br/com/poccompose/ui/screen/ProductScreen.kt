package br.com.poccompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.poccompose.ui.components.AppCapitalizedSession
import br.com.poccompose.ui.components.AppImageCardItem
import br.com.poccompose.ui.components.AppState
import br.com.poccompose.ui.components.objects.CardImageItemProduct
import br.com.poccompose.ui.components.rememberAppState


@Composable
fun ProductsScreen(
    appState: AppState,
    modifier: Modifier = Modifier
){
    //TODO Consider put this into a component
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .padding(end = 6.dp)
                .verticalScroll(rememberScrollState())

        ) {
            AppCapitalizedSession("A") {
                AppImageCardItem(appState = appState, CardImageItemProduct(
                    "Ass iPhone 7 plus",
                    "48",
                    "R$ 6.145,00"
                    )
                )
                AppImageCardItem(appState = appState, CardImageItemProduct(
                    "Ass iPhone 7 plus live",
                    "110",
                    "R$ 4.145,00"
                    )
                )
            }
            AppCapitalizedSession("B") {
                AppImageCardItem(appState = appState, CardImageItemProduct(
                    "Bass iPhone 7 plus",
                    "48",
                    "R$ 6.145,00"
                    )
                )
                AppImageCardItem(appState = appState, CardImageItemProduct(
                    "Bass iPhone 7 plus live",
                    "110",
                    "R$ 4.145,00"
                    )
                )
                AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                    "Bass iPhone 7 plus live",
                    "110",
                    "R$ 4.145,00"
                    )
                )
            }
            AppCapitalizedSession("C") {
                AppImageCardItem(appState = appState, CardImageItemProduct(
                    "Compass iPhone 7 plus",
                    "48",
                    "R$ 6.145,00"
                )
                )
                AppImageCardItem(appState = appState, CardImageItemProduct(
                    "Compass iPhone 7 plus live",
                    "110",
                    "R$ 4.145,00"
                )
                )
                AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                    "Compass iPhone 11 plus live",
                    "11",
                    "R$ 4.145,00"
                )
                )
                AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                    "Compass iPhone 11 plus live",
                    "11",
                    "R$ 4.145,00"
                )
                )
                AppImageCardItem(appState = rememberAppState(), CardImageItemProduct(
                    "Compass iPhone 11 plus live last",
                    "11",
                    "R$ 4.145,00"
                )
                )
            }
        }

}