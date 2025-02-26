package com.zaed.changedinar.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyEntity(
    val code: String,
    val id: Int,
    val name: String,
    val price: Price,
    val symbol: String
)


fun CurrencyEntity.toCurrency(): Currency {
    return Currency(
        icon = CurrencyIcon.valueOf(code),
        name = name,
        code = code,
        id = id,
        officialPrice = price.official_price.toDoubleOrNull() ?: 0.0,
        unofficialBuyPrice = price.unofficial_buy_price.toDoubleOrNull() ?: 0.0,
        unofficialSellPrice = price.unofficial_sell_price.toDoubleOrNull() ?: 0.0
    )
}

