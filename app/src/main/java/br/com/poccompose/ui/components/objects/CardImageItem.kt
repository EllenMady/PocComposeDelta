package br.com.poccompose.ui.components.objects

import androidx.compose.ui.graphics.Color

interface CardImageItem {
    fun getTitle(): String
    fun getSubtitle(): String
    fun getLeftBottomInfo(): String
    fun getLeftBottomInfoColor():Color
}