package com.zaed.changedinar.ui.crypto

import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.ui.electronic.format
import java.util.Date

data class CryptoUiState(
    val cryptoList: List<CryptoModel> = emptyList(),
    val lastUpdate: String = Date().format(),
    val isLoading: Boolean = false,
    val error: String? = null
)