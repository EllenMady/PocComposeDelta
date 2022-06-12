package br.com.poccompose.ui.components.objects

import androidx.compose.ui.graphics.Color

data class CardImageItemProduct(
    val name: String,
    val quantity: String,
    val price: String

): CardImageItem {
    override fun getTitle() = name

    override fun getSubtitle()= quantity

    override fun getLeftBottomInfo()= price

    override fun getLeftBottomInfoColor()= Color.Red
}
