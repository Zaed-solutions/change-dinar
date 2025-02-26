package com.zaed.changedinar.ui.crypto

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.data.source.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoViewModel (
private val remoteDataSource: RemoteDataSource
):ViewModel(){
    private val _uiState = MutableStateFlow(CryptoUiState())
    val uiState = _uiState.asStateFlow()
    init {
        fetchCrypto()
    }

    private fun fetchCrypto() {
        Log.d("TAG", "fetchCrypto:vm started ")
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            remoteDataSource.fetchCrypto().onSuccess { data->
                Log.d("TAG", "fetchCrypto vm: $data")
                _uiState.update {
                    it.copy(cryptoList = data, isLoading = false)
                }
            }.onFailure {
                Log.d("TAG", "fetchCrypto vm: $it")
                _uiState.update {
                    it.copy(error = it.error, isLoading = false)
                }
            }


        }
    }

}

data class CryptoUiState(
    val cryptoList: List<CryptoModel> = emptyList(),
    val lastUpdate: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
