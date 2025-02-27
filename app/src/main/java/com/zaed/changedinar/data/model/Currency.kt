package com.zaed.changedinar.data.model

data class Currency(
    val icon: CurrencyIcon = CurrencyIcon.USD,
    val id: Int = 0,
    val code: String = "",
    val name: String = "",
    val date: String = "",
    val symbol: String = "",
    val officialPrice: Double = 0.0,
    val unofficialSellPrice: Double = 0.0,
    val unofficialBuyPrice: Double = 0.0,
)
