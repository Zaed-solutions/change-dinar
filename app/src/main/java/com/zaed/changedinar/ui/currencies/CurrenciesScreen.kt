package com.zaed.changedinar.ui.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.data.model.CurrencyIcon
import com.zaed.changedinar.ui.currencies.components.CurrenciesList
import com.zaed.changedinar.ui.currencies.components.CurrenciesTopAppBar
import com.zaed.changedinar.ui.theme.ChangeDinarTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrenciesScreen(
    modifier: Modifier = Modifier,
    viewModel: CurrenciesViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    CurrenciesScreenContent(
        modifier = modifier,
        state = state
    ) { action ->
        when(action){
            else -> viewModel.handleAction(action)
        }
    }
}


@Composable
fun CurrenciesScreenContent(
    modifier: Modifier = Modifier,
    state: CurrenciesUiState,
    onAction: (CurrenciesUiAction) -> Unit
) {
    Scaffold (
        modifier = modifier,
        containerColor = Color.Transparent,
        topBar = {
            CurrenciesTopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                onRefreshClicked = {
                    onAction(CurrenciesUiAction.OnRefreshClicked)
                },
                onInfoClicked = {
                    onAction(CurrenciesUiAction.OnInfoClicked)
                },
                onOfficialSwitchClicked = {
                    onAction(CurrenciesUiAction.OnOfficialSwitchClicked(it))
                },
                isOfficial = state.isOfficial,
                lastRefreshed = state.lastRefreshed
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            androidx.compose.animation.AnimatedVisibility(state.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp),
                    color = Color(0xFFA3D8FF),

                    )
            }
            CurrenciesList(
                modifier = Modifier,
                isOfficial = state.isOfficial,
                currencies = state.currencies
            )
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    ChangeDinarTheme {
        CurrenciesScreenContent(
            state = CurrenciesUiState(
                currencies = listOf(
                    Currency(
                        icon = CurrencyIcon.AED,
                        name = "UAE Dirham",
                        code = "AED",
                        id = 1,
                        officialPrice = 542.8585,
                        unofficialSellPrice = 982.5,
                        unofficialBuyPrice = 451742.5,
                    )
                )
            ),
            onAction = {}
        )
    }
}

