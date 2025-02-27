package com.zaed.changedinar.ui.currencies

import com.zaed.changedinar.data.model.Currency

data class CurrenciesUiState(
    val isLoading: Boolean = true,
    val currencies: List<Currency> = emptyList(),
    val isOfficial: Boolean = false,
    val lastRefreshed: String = ""
)
