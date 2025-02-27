package com.zaed.changedinar.ui.electronic.component


import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.zaed.changedinar.data.model.ElectronicCurrency
import com.zaed.changedinar.ui.crypto.component.CryptoItem

@Composable
fun ElectronicsList(
    modifier: Modifier = Modifier,
    electronicList: List<ElectronicCurrency> = emptyList(),
    selectedTab: ElectronicCurrencyTab = ElectronicCurrencyTab.EURO,
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            items = electronicList,
            key = { _, elctro -> elctro.id } // Assuming CryptoModel has a unique id
        ) { index, electronicCurrency ->
            val animatedVisibility = remember { androidx.compose.animation.core.Animatable(0f) }

            LaunchedEffect(key1 = Unit) {
                animatedVisibility.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = index * 30
                    )
                )
            }

            Box(
                modifier = Modifier
                    .alpha(animatedVisibility.value)
                    .offset(y = (20 * (1f - animatedVisibility.value)).dp)
            ) {
                ElectronicCurrencyItem(electronicCurrency = electronicCurrency, selectedTab = selectedTab)
            }
        }

        item {
            Spacer(Modifier.height(100.dp))
        }
    }
}