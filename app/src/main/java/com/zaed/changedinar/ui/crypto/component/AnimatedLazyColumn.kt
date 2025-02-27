package com.zaed.changedinar.ui.crypto.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> AnimatedLazyColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    animationDurationMillis: Int = 500,
    animationDelayPerItemMillis: Int = 30,
    slideDistance: Int = 20,
    footerSpacerHeight: Dp = 100.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(0.dp),
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        modifier = modifier
    ) {
        itemsIndexed(
            items = items,
        ) { index, item ->
            val animatedVisibility = remember { Animatable(0f) }

            LaunchedEffect(key1 = Unit) {
                animatedVisibility.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = animationDurationMillis,
                        delayMillis = index * animationDelayPerItemMillis
                    )
                )
            }

            Box(
                modifier = Modifier
                    .alpha(animatedVisibility.value)
                    .offset(y = (slideDistance * (1f - animatedVisibility.value)).dp)
            ) {
                itemContent(index, item)

            }
        }
        item {
            Spacer(Modifier.height(footerSpacerHeight))
        }


    }
}