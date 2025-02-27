package com.zaed.changedinar.ui.converter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.zaed.changedinar.R
import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.data.model.CurrencyIcon
import com.zaed.changedinar.ui.converter.components.BreathingAppIcon
import com.zaed.changedinar.ui.converter.components.ConverterTopAppBar
import com.zaed.changedinar.ui.converter.components.ConvertibleCurrencyItem
import com.zaed.changedinar.ui.converter.components.SelectCurrencyBottomSheet
import com.zaed.changedinar.ui.theme.ChangeDinarTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConverterScreen(
    modifier: Modifier = Modifier,
    viewModel: ConverterViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    ConverterScreenContent(
        state = state,
        onAction = viewModel::handleAction
    )

}

@Composable
fun ConverterScreenContent(
    modifier: Modifier = Modifier,
    state: ConverterUiState,
    onAction: (ConverterUiAction) -> Unit
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    Scaffold(
        containerColor = Color.Transparent,
        modifier = modifier.fillMaxSize(),
        topBar = {
            ConverterTopAppBar(
                lastRefreshed = state.lastRefreshed,
                onRefreshClicked = {
                    onAction(ConverterUiAction.OnRefreshClicked)
                },
                onInfoClicked = {
                    onAction(ConverterUiAction.OnInfoClicked)
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier)
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConvertibleCurrencyItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 150.dp),
                    currency = state.selectedCurrency,
                    focusRequester = focusRequester,
                    price = state.originalAmount,
                    isConvertible = true,
                    onClick = {
                        isBottomSheetVisible = true
                    },
                    onValueChanged = {
                        onAction(ConverterUiAction.OnConvertedCurrencyValueChanged(it))
                    }
                )
                //convert icon
                Icon(
                    imageVector = Icons.Default.SwapVert,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = CircleShape
                        )
                        .padding(8.dp)
                )
                //dz currency
                ConvertibleCurrencyItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    currency = state.dzCurrency,
                    price = state.convertedAmount,
                    isConvertible = false,
                )
            }
            BreathingAppIcon(
                modifier = Modifier.padding(bottom= 96.dp),
            )
            Spacer(modifier = Modifier)
            //bottom sheet
            SelectCurrencyBottomSheet(
                isVisible = isBottomSheetVisible,
                onDismiss = {
                    isBottomSheetVisible = false
                },
                onCurrencyClicked = {
                    onAction(ConverterUiAction.OnCurrencySelected(it))
                    isBottomSheetVisible = false
                    focusRequester.requestFocus()
                },
                currencies = state.currencies,
                selectedCurrency = state.selectedCurrency
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_9_pro")
@Composable
private fun Preview() {
    ChangeDinarTheme {
        val state = ConverterUiState(
            selectedCurrency = Currency(
                icon = CurrencyIcon.EUR,
                code = "EUR",
                name = "Euro",
                symbol = "$",
                officialPrice = 500.0
            ),
            dzCurrency = Currency(
                icon = CurrencyIcon.USD,
                code = "USD",
                name = "US Dollar",
                symbol = "$",
            ),
            originalAmount = 12.0,
            lastRefreshed = "2025-02-27"
        )
        ConverterScreenContent(
            state = state
        ) { }
    }
}