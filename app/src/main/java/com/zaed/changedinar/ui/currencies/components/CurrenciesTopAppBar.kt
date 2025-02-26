package com.zaed.changedinar.ui.currencies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrenciesTopAppBar(
    modifier: Modifier = Modifier,
    onRefreshClicked: () -> Unit,
    onInfoClicked: () -> Unit,
    lastRefreshed: String,
    isOfficial: Boolean,
    onOfficialSwitchClicked: (Boolean) -> Unit,
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
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
        Row{
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
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth(),
        ){
            SegmentedButton(
                modifier= Modifier.weight(1f),
                selected = !isOfficial,
                onClick = {
                    onOfficialSwitchClicked(false)
                },
                shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                icon = {},
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = Color(0xFF0095FF),
                    activeContentColor = Color.White,
                    activeBorderColor = Color.Transparent,
                    inactiveContainerColor = Color(0xFFFEFEFE),
                    inactiveContentColor = Color.Black,
                    inactiveBorderColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Parallel",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        lineHeight = 19.sp
                    )
                )
            }
            SegmentedButton(
                modifier= Modifier.weight(1f),
                selected = isOfficial,
                onClick = {
                    onOfficialSwitchClicked(true)
                },
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                icon = {},
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = Color(0xFF0095FF),
                    activeContentColor = Color.White,
                    activeBorderColor = Color.Transparent,
                    inactiveContainerColor = Color(0xFFFEFEFE),
                    inactiveContentColor = Color.Black,
                    inactiveBorderColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Officiel",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        lineHeight = 19.sp
                    )
                )
            }
        }
    }

}