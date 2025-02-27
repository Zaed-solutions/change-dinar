package com.zaed.changedinar.ui.electronic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zaed.changedinar.ui.crypto.component.CryptoScreenContent
import com.zaed.changedinar.ui.electronic.component.ElectronicsScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun ElectronicsScreen(
    modifier: Modifier = Modifier,
    viewModel: ElectronicViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ElectronicsScreenContent(
        modifier = modifier,
        uiState = uiState,
        onRefresh = viewModel::fetchElectronic
    )
}

