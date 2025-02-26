package com.zaed.changedinar.ui.currencies

import com.zaed.changedinar.data.model.Currency

data class CurrenciesUiState(
    val isLoading: Boolean = false,
    val currencies: List<Currency> = emptyList(),
    val isOfficial: Boolean = false,
    val lastRefreshed: String = ""
)
