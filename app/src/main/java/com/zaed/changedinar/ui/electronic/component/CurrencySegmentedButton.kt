package com.zaed.changedinar.ui.electronic.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaed.changedinar.R
import com.zaed.changedinar.ui.theme.ChangeDinarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencySegmentedButton(
    modifier: Modifier = Modifier,
    width : Dp = 220.dp,
    selectedTab: ElectronicCurrencyTab = ElectronicCurrencyTab.EURO,
    onOptionSelected: (ElectronicCurrencyTab) -> Unit = {}
) {

    val backgroundColor = Color(0xFFF2F5F9)
    val selectedBackgroundColor = Color(0xFF0A84FF)
    val unselectedTextColor = Color(0xFF2E3A59)

    val offsetX by animateDpAsState(
        targetValue = if (selectedTab == ElectronicCurrencyTab.EURO) 0.dp else width*0.48f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )
    Box (
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(1.dp, Color(0xFFE0E5EC), RoundedCornerShape(20.dp))
            .padding(4.dp),
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(32.dp)
                .offset(x=offsetX)
                .clip(RoundedCornerShape(20.dp))
                .background(selectedBackgroundColor)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth().background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElectronicCurrencyTab.entries.forEach { option ->
                val isSelected = option == selectedTab

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
//                        .background(backgroundColor)
                        .clickable {
                            onOptionSelected(option)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Flag icon
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            AnimatedContent(option) {
                                when (it) {
                                    ElectronicCurrencyTab.EURO -> {
                                        Image(
                                            painter = painterResource(R.drawable.ic_euro),
                                            contentDescription = null,
                                            modifier = Modifier.size(20.dp)

                                        )
                                    }

                                    ElectronicCurrencyTab.DOLLAR -> {
                                        Image(
                                            painter = painterResource(R.drawable.ic_usd),
                                            contentDescription = null,
                                            modifier = Modifier.size(20.dp)

                                        )
                                    }
                                }

                            }
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        // Currency code
                        Text(
                            text = option.currencyCode,
                            color = if (isSelected) Color.White else unselectedTextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CurrencySegmentedButtonPreview() {
    ChangeDinarTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CurrencySegmentedButton(
                modifier = Modifier.width(220.dp),
                onOptionSelected = { currency ->
                    // Handle selection
                }
            )
        }
    }
}