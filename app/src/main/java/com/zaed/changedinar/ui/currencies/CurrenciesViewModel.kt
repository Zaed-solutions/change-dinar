package com.zaed.changedinar.ui.currencies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaed.changedinar.data.source.remote.RemoteDataSource
import com.zaed.changedinar.ui.util.DateFormat
import com.zaed.changedinar.ui.util.format
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class CurrenciesViewModel(
    private val remoteSource: RemoteDataSource
): ViewModel() {
    private val TAG = "CurrenciesViewModel"
    private val _uiState = MutableStateFlow(CurrenciesUiState())
    val uiState = _uiState.asStateFlow()
    init {
        fetchCurrencies()
    }
    private fun fetchCurrencies(){
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch (Dispatchers.IO){
            remoteSource.fetchCurrencies().onSuccess { data ->
                _uiState.update {
                    it.copy(
                        currencies = data,
                        isLoading = false,
                        lastRefreshed = data.firstOrNull()?.date?:""
                    )
                }
            }.onFailure { e ->
                Log.e(TAG, "fetchCurrencies: ${e.message}", e)
            }
        }
    }

    fun handleAction(action: CurrenciesUiAction) {
        when(action){
            CurrenciesUiAction.OnInfoClicked -> {
//                TODO()
            }
            is CurrenciesUiAction.OnOfficialSwitchClicked -> updateIsOfficial(action.isOfficial)
            CurrenciesUiAction.OnRefreshClicked -> fetchCurrencies()
        }
    }

    private fun updateIsOfficial(isOfficial: Boolean) {
        _uiState.update { it.copy(isOfficial = isOfficial) }
    }
}