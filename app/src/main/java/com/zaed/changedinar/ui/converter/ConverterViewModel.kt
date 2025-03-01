package com.zaed.changedinar.ui.converter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.data.source.remote.RemoteDataSource
import com.zaed.changedinar.ui.util.DateFormat
import com.zaed.changedinar.ui.util.format
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class ConverterViewModel(
    private val remoteSource: RemoteDataSource
): ViewModel() {
    private val TAG = "ConvertedViewModel"
    private val _uiState = MutableStateFlow(ConverterUiState())
    val uiState = _uiState.asStateFlow()
    init{
        fetchCurrencies()
    }
    private fun fetchCurrencies(){
        viewModelScope.launch (Dispatchers.IO){
            remoteSource.fetchCurrencies().onSuccess { data ->
                _uiState.update {
                    it.copy(
                        currencies = data,
                        selectedCurrency = data.firstOrNull()?: Currency(),
                        lastRefreshed = data.firstOrNull()?.date?:""
                    )
                }
            }.onFailure { e ->
                Log.e(TAG, "fetchCurrencies: ${e.message}", e)
            }
        }
    }

    fun handleAction(action: ConverterUiAction) {
        when(action){
            is ConverterUiAction.OnConvertedCurrencyValueChanged -> updateOriginalAmount(action.value)
            is ConverterUiAction.OnCurrencySelected -> updateSelectedCurrency(action.currency)
            ConverterUiAction.OnRefreshClicked -> fetchCurrencies()
            else -> Unit
        }
    }

    private fun updateSelectedCurrency(currency: Currency) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedCurrency = currency,
                    originalAmount = 0.0,
                    convertedAmount = 0.0
                )
            }
        }
    }

    private fun updateOriginalAmount(amount: Double) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    originalAmount = amount,
                    convertedAmount = amount * it.selectedCurrency.unofficialBuyPrice)
            }
        }
    }
}