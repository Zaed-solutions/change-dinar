package com.zaed.changedinar.ui.currencies.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaed.changedinar.data.model.Currency

@Composable
fun CurrenciesList(
    modifier: Modifier = Modifier,
    isOfficial: Boolean,
    currencies: List<Currency>
) {
    LazyColumn (
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(currencies){ currency ->
            CurrencyItem(
                modifier = Modifier.animateItem(),
                isOfficial = isOfficial,
                currency = currency
            )
        }
    }

}