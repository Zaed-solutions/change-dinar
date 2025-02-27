package com.zaed.changedinar.ui.crypto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zaed.changedinar.ui.crypto.component.CryptoScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun CryptoScreen(
    modifier: Modifier = Modifier,
    viewModel: CryptoViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    CryptoScreenContent(
        modifier = modifier,
        uiState = uiState,
        onRefresh = viewModel::fetchCrypto
    )
}

