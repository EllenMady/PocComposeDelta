package br.com.poccompose.ui.components.objects

import br.com.poccompose.real.enums.IconTypeEnum

data class TopBarAction(
    val icon: Int,
    val iconType: IconTypeEnum = IconTypeEnum.IMAGE,
    val onClick: () -> Unit
)