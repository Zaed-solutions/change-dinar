package com.zaed.changedinar.ui.currencies.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.data.model.CurrencyIcon
import com.zaed.changedinar.ui.theme.ChangeDinarTheme

@Composable
fun CurrencyItem(
    modifier: Modifier = Modifier,
    isOfficial: Boolean,
    currency: Currency,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(currency.icon.iconRes),
                contentDescription = "Currency Icon",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(84.dp),
            ) {
                Text(
                    text = currency.code,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp,
                        lineHeight = 20.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = currency.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                        lineHeight = 15.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            AnimatedContent(isOfficial) {state ->
                when{
                    state ->{
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ){
                            PriceItem(
                                title = "Officiel",
                                price = currency.officialPrice,
                                isIncreasing = false
                            )

                        }
                    }
                    else -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ){
                            PriceItem(
                                modifier = Modifier.padding(start = 4.dp),
                                title = "Vente",
                                price = currency.unofficialSellPrice,
                                isIncreasing = currency.unofficialSellPrice > currency.officialPrice
                            )
                            PriceItem(
                                modifier = Modifier.padding(start = 12.dp),
                                title = "Achat",
                                price = currency.unofficialBuyPrice,
                                isIncreasing = currency.unofficialBuyPrice > currency.officialPrice
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_9_pro")
@Composable
private fun Preview() {
    val currency = Currency(
        icon = CurrencyIcon.USD,
        id = "1",
        code = "USD",
        name = "US Dollar",
        officialPrice = 250.0,
        unofficialSellPrice = 251.0,
        unofficialBuyPrice = 253.0,
    )
    ChangeDinarTheme {
        CurrencyItem(
            modifier = Modifier.padding(horizontal = 16.dp,
                vertical = 32.dp),
            isOfficial = false,
            currency = currency
        )
    }
}