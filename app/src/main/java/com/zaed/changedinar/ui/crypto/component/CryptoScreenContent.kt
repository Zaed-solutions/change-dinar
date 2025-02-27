package com.zaed.changedinar.ui.crypto.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zaed.changedinar.ui.crypto.CryptoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreenContent(
    modifier: Modifier,
    uiState: CryptoUiState,
    onRefresh: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                windowInsets = WindowInsets(0),
                title = {
                    Text(text = "Crypto")
                },
                actions = {
                    IconButton(
                        onClick = onRefresh,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Replay,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .linearBackground()
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Dernière mise à jour: ${uiState.lastUpdate}")
                CryptoList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    cryptoList = uiState.cryptoList,
                )
            }

        }
    }
}

