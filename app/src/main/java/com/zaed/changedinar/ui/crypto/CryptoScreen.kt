package com.zaed.changedinar.ui.crypto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zaed.changedinar.ui.components.CryptoItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun CryptoScreen(
    modifier: Modifier = Modifier,
    viewModel: CryptoViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CryptoScreenContent(
        modifier = modifier,
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreenContent(
    modifier: Modifier,
    uiState: CryptoUiState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                windowInsets = WindowInsets(0),
                title = {
                    Text(text = "Crypto")
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }
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
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFFFF),
                            Color(0xFFA3D8FF)
                        ), // Adjust colors as needed
                        start = Offset(0f, 0f),
                        end = Offset(0f, 1000f),
                    )
                )
                .padding(paddingValues),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    ,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ){
                Text(text = "Last Update: ${uiState.lastUpdate}")
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 33.dp, bottom = 12.dp)
                ) {
                    items(uiState.cryptoList) { crypto ->
                        CryptoItem(cryptoModel = crypto)
                    }
                }
            }

        }
    }
}
