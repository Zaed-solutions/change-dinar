package com.zaed.changedinar.ui.currencies.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaed.changedinar.R
import com.zaed.changedinar.ui.theme.ChangeDinarTheme

@Composable
fun PriceItem(
    modifier: Modifier = Modifier,
    title: String,
    price: Double,
    isIncreasing: Boolean,
) {
    val icon = remember(isIncreasing){
        if(isIncreasing) {
            R.drawable.ic_arrow_up
        } else {
            R.drawable.ic_arrow_down
        }
    }
    Column (
        modifier = modifier.width(84.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp,
                lineHeight = 15.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
           modifier = Modifier
               .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Text(
                text = "$price",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            /*
            Image(
                painter = painterResource(icon),
                contentDescription = "Currency Icon",
//                modifier= Modifier.width(13.dp)
            )
            */

        }
    }
}

@Preview(showBackground = true, device = "id:pixel_9_pro")
@Composable
private fun Preview() {
    ChangeDinarTheme {
        PriceItem(
            title = "Vente",
            price = 250.0,
            isIncreasing = true
        )
    }
}