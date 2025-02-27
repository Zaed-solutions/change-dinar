package com.zaed.changedinar.ui.electronic

import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.data.model.ElectronicCurrency
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ElectronicUiState(
    val electronicList: List<ElectronicCurrency> = emptyList(),
    val lastUpdate: String = Date().format(),
    val isLoading: Boolean = false,
)


fun Date.format(dateFormat:String =  "d MMM, yyyy, hh:mm a"): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.US)
    return formatter.format(this)
}