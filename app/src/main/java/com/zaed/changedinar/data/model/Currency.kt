package com.zaed.changedinar.data.model

data class Currency(
    val icon: CurrencyIcon,
    val id: String,
    val code: String,
    val name: String,
    val officialPrice: Double,
    val unofficialSellPrice: Double,
    val unofficialBuyPrice: Double,
)
