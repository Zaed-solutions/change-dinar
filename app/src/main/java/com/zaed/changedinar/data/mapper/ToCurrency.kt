package com.zaed.changedinar.data.mapper

import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.data.model.CurrencyEntity
import com.zaed.changedinar.data.model.CurrencyIcon

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