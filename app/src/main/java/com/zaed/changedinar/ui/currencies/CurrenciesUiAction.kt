package com.zaed.changedinar.ui.currencies

sealed interface CurrenciesUiAction {
    data object OnRefreshClicked : CurrenciesUiAction
    data object OnInfoClicked : CurrenciesUiAction
    data class OnOfficialSwitchClicked(val isOfficial: Boolean) : CurrenciesUiAction
}