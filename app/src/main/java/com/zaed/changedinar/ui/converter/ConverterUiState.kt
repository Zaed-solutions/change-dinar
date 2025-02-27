package com.zaed.changedinar.ui.converter

import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.data.model.CurrencyIcon

data class ConverterUiState(
    val currencies: List<Currency> = emptyList(),
    val selectedCurrency: Currency = Currency(),
    val dzCurrency: Currency = Currency(
        icon = CurrencyIcon.DZD,
        name = "Dinar Algérien",
        code = "DZD",
        symbol = "DA",
    ),
    val originalAmount: Double = 0.0,
    val convertedAmount: Double = 0.0,
    val lastRefreshed: String = ""
)
