package com.zaed.changedinar.ui.crypto.component

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.linearBackground(
    topColor: Color = Color(0xFFFFFF),
    bottomColor: Color = Color(0xFFA3D8FF)
): Modifier =
    this.background(
        Brush.linearGradient(
            colors = listOf(
                topColor,
                bottomColor
            ), // Adjust colors as needed
            start = Offset(0f, 0f),
            end = Offset(0f, 1000f),
        )
    )