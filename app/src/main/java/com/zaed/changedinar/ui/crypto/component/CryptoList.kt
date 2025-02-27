package com.zaed.changedinar.ui.crypto.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.zaed.changedinar.data.model.CryptoModel
import kotlinx.coroutines.delay
@Composable
fun CryptoList(
    modifier: Modifier = Modifier,
    cryptoList: List<CryptoModel> = emptyList(),
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            items = cryptoList,
            key = { _, crypto -> crypto.crypto_code } // Assuming CryptoModel has a unique id
        ) { index, crypto ->
            val animatedVisibility = remember { androidx.compose.animation.core.Animatable(0f) }

            LaunchedEffect(key1 = Unit) {
                // Only animate when first composed, not on recomposition
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
                CryptoItem(cryptoModel = crypto)
            }
        }

        item {
            Spacer(Modifier.height(100.dp))
        }
    }
}