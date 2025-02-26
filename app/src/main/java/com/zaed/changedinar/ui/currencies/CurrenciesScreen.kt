package com.zaed.changedinar.ui.currencies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
        CurrenciesList(
            modifier = Modifier.padding(innerPadding),
            isOfficial = state.isOfficial,
            currencies = state.currencies
        )
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
                        name = "United Arab Emirates Dirham",
                        code = "AED",
                        id = "1",
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

