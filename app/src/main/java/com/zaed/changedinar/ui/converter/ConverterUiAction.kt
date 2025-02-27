package com.zaed.changedinar.ui.converter

import com.zaed.changedinar.data.model.Currency

sealed interface ConverterUiAction {
    data object OnRefreshClicked: ConverterUiAction
    data object OnInfoClicked: ConverterUiAction
    data class OnConvertedCurrencyValueChanged(val value: Double) : ConverterUiAction
    data class OnCurrencySelected(val currency: Currency) : ConverterUiAction
}