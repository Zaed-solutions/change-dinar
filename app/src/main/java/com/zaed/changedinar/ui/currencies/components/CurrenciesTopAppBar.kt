package com.zaed.changedinar.ui.currencies.components

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaed.changedinar.R
import com.zaed.changedinar.ui.electronic.component.ElectronicCurrencyTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrenciesTopAppBar(
    modifier: Modifier = Modifier,
    onRefreshClicked: () -> Unit,
    onInfoClicked: () -> Unit,
    lastRefreshed: String,
    isOfficial: Boolean,
    onOfficialSwitchClicked: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onRefreshClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = "Taux de Change",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp,
                    lineHeight = 20.sp
                )
            )
            IconButton(
                onClick = {
                    onInfoClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Info",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row {
            Text(
                text = "Dernière mise à jour : ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 15.sp,
                    lineHeight = 15.sp
                )
            )
            Text(
                text = lastRefreshed,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        SegmentedButton(
            modifier = Modifier.width(220.dp),
            isOfficial = isOfficial,
            onOfficialSwitchClicked = onOfficialSwitchClicked
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SegmentedButton(
    modifier : Modifier = Modifier,
    isOfficial: Boolean,
    width : Dp = 220.dp,
    onOfficialSwitchClicked: (Boolean) -> Unit
) {
        val backgroundColor = Color(0xFFF2F5F9)
        val selectedBackgroundColor = Color(0xFF0A84FF)
        val unselectedTextColor = Color(0xFF2E3A59)

        val offsetX by animateDpAsState(
            targetValue = if (!isOfficial) 0.dp else width*0.48f,
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

                listOf("Parallel","Officiel").forEachIndexed { index, item  ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(16.dp))
//                        .background(backgroundColor)
                            .clickable {
                                onOfficialSwitchClicked(index==1)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                            // Currency code
                            Text(
                                text = item,
                                color = if (isOfficial && index==1 || !isOfficial && index==0) Color.White else unselectedTextColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )

                    }
                }
            }

        }

//    SingleChoiceSegmentedButtonRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 10.dp),
//    ) {
//        SegmentedButton(
//            modifier = Modifier.weight(1f),
//            selected = !isOfficial,
//            onClick = {
//                onOfficialSwitchClicked(false)
//            },
//            shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
//            icon = {},
//            colors = SegmentedButtonDefaults.colors(
//                activeContainerColor = Color(0xFF0095FF),
//                activeContentColor = Color.White,
//                activeBorderColor = Color.Transparent,
//                inactiveContainerColor = Color(0xFFFEFEFE),
//                inactiveContentColor = Color.Black,
//                inactiveBorderColor = Color.Transparent
//            )
//        ) {
//            Text(
//                text = "Parallel",
//                style = MaterialTheme.typography.titleMedium.copy(
//                    fontSize = 16.sp,
//                    lineHeight = 19.sp
//                )
//            )
//        }
//        SegmentedButton(
//            modifier = Modifier.weight(1f),
//            selected = isOfficial,
//            onClick = {
//                onOfficialSwitchClicked(true)
//            },
//            shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
//            icon = {},
//            colors = SegmentedButtonDefaults.colors(
//                activeContainerColor = Color(0xFF0095FF),
//                activeContentColor = Color.White,
//                activeBorderColor = Color.Transparent,
//                inactiveContainerColor = Color(0xFFFEFEFE),
//                inactiveContentColor = Color.Black,
//                inactiveBorderColor = Color.Transparent
//            )
//        ) {
//            Text(
//                text = "Officiel",
//                style = MaterialTheme.typography.titleMedium.copy(
//                    fontSize = 16.sp,
//                    lineHeight = 19.sp
//                )
//            )
//        }
//    }
}


@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_9_pro")
@Composable
private fun Preview() {
    CurrenciesTopAppBar(
        modifier = Modifier.padding(top = 24.dp),
        lastRefreshed = "2022-02-02 02:02:02",
        isOfficial = true,
        onRefreshClicked = {},
        onInfoClicked = {},
        onOfficialSwitchClicked = {}
    )
}