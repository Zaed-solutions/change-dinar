package com.zaed.changedinar.ui.electronic.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zaed.changedinar.R
import com.zaed.changedinar.data.model.ElectronicCurrency
import com.zaed.changedinar.ui.crypto.component.linearBackground
import com.zaed.changedinar.ui.electronic.ElectronicUiState
import com.zaed.changedinar.ui.theme.ChangeDinarTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectronicsScreenContent(
    modifier: Modifier,
    uiState: ElectronicUiState,
    onRefresh: () -> Unit = {}
) {
    Scaffold(
        contentWindowInsets = WindowInsets( 0),
                containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                        windowInsets = WindowInsets(0),
                title = {
                    Text(text = "Monnaie Numérique")
                },
                actions = {
                    IconButton(
                        onClick = onRefresh
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
                androidx.compose.animation.AnimatedVisibility(uiState.isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp),
                        color = Color(0xFFA3D8FF),

                    )
                }
                var selectedTab by remember { mutableStateOf(ElectronicCurrencyTab.EURO) }
                CurrencySegmentedButton(
                    modifier = Modifier.padding(vertical = 16.dp).width(250.dp),
                    width = 250.dp,
                    selectedTab = selectedTab,
                    onOptionSelected = { currency ->
                        selectedTab = currency
                    }
                )
                Text(text = "Dernière mise à jour: ${uiState.lastUpdate}")
                ElectronicsList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    electronicList = uiState.electronicList,
                    selectedTab = selectedTab
                )
            }

        }
    }
}

@Preview
@Composable
private fun ElectronicScreenContentPreview() {
    ChangeDinarTheme {
        ElectronicsScreenContent(
            modifier = Modifier,
            uiState = ElectronicUiState(
                electronicList = listOf(
                    ElectronicCurrency(
                        id = 1,
                        currency_id = 1,
                        icon = R.drawable.ic_paypal,
                        currency_name = "Euro",
                        eur_to_dzd_buy = 2.0,
                        eur_to_dzd_sell = 3.0,
                        usd_to_dzd_buy = 4.0,
                        usd_to_dzd_sell = 5.0,
                        recorded_at = Date()
                    )
                )
            )
        )
    }
    
}

