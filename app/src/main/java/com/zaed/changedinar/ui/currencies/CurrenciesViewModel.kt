package com.zaed.changedinar.ui.currencies

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CurrenciesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CurrenciesUiState())
    val uiState = _uiState.asStateFlow()
    fun handleAction(action: CurrenciesUiAction) {

    }
}