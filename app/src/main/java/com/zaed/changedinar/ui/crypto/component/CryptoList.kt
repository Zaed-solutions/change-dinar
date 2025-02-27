package com.zaed.changedinar.ui.crypto.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zaed.changedinar.data.model.CryptoModel

@Composable
fun CryptoList(
    modifier: Modifier = Modifier,
    cryptoList: List<CryptoModel> = emptyList(),
) {
    AnimatedLazyColumn(
        modifier = modifier,
        items = cryptoList,
        itemContent = { _, item ->
            CryptoItem(
                cryptoModel = item
            )
        },

    )
}

