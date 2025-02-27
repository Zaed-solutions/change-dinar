package com.zaed.changedinar.ui.currencies.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.ui.crypto.component.AnimatedLazyColumn

@Composable
fun CurrenciesList(
    modifier: Modifier = Modifier,
    isOfficial: Boolean,
    currencies: List<Currency>
) {
    AnimatedLazyColumn(
        modifier = modifier.fillMaxWidth(),
        items = currencies,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) { _, item ->
        CurrencyItem(
            modifier = Modifier,
            isOfficial = isOfficial,
            currency = item
        )
    }
//    LazyColumn(
//        modifier = modifier.fillMaxWidth(),
//        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        items(
//            items = currencies,
//            key = { it.id }
//        ) { currency ->
//            C
//        }
//    }

}