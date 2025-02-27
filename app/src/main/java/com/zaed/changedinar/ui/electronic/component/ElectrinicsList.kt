package com.zaed.changedinar.ui.electronic.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zaed.changedinar.data.model.ElectronicCurrency
import com.zaed.changedinar.ui.crypto.component.AnimatedLazyColumn

@Composable
fun ElectronicsList(
    modifier: Modifier = Modifier,
    electronicList: List<ElectronicCurrency> = emptyList(),
    selectedTab: ElectronicCurrencyTab = ElectronicCurrencyTab.EURO,
) {
    AnimatedLazyColumn(
        modifier = modifier.fillMaxWidth(),
        items = electronicList,
    ) { _, item ->
        ElectronicCurrencyItem(
            modifier = Modifier,
            electronicCurrency = item,
            selectedTab = selectedTab
        )
    }
}