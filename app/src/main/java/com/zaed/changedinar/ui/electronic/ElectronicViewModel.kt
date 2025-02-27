package com.zaed.changedinar.ui.electronic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaed.changedinar.data.source.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class ElectronicViewModel (
private val remoteDataSource: RemoteDataSource
):ViewModel(){
    private val _uiState = MutableStateFlow(ElectronicUiState())
    val uiState = _uiState.asStateFlow()
    init {
        fetchElectronic()
    }

    fun fetchElectronic() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            remoteDataSource.fetchElectronic().onSuccess { data->
                Log.d("TAG", "fetchElectro vm: $data")
                _uiState.update {
                    it.copy(electronicList = data, isLoading = false, lastUpdate = data.firstOrNull()?.recorded_at?.format()?: Date().format())
                }
            }.onFailure {
                Log.d("TAG", "fetchElectro vm: $it")
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }


        }
    }

}

