package com.zaed.changedinar.ui.electronic.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaed.changedinar.R
import com.zaed.changedinar.data.model.ElectronicCurrency
import com.zaed.changedinar.ui.theme.ChangeDinarTheme
import java.util.Date

enum class ElectronicCurrencyTab(
    val title: String,
    @DrawableRes val icon: Int,
    val currencyCode: String
) {
    //todo make it french
    EURO("Euro", R.drawable.ic_euro, "EUR"),
    DOLLAR("Dollar", R.drawable.ic_usd, "USD");
}

@Composable
fun ElectronicCurrencyItem(
    modifier: Modifier = Modifier,
    electronicCurrency: ElectronicCurrency,
    selectedTab: ElectronicCurrencyTab = ElectronicCurrencyTab.EURO,
) {
    val minValue by rememberUpdatedState(
        newValue =
        when (selectedTab) {
            ElectronicCurrencyTab.EURO -> electronicCurrency.eur_to_dzd_sell.toBigDecimal()
                .setScale(2)

            ElectronicCurrencyTab.DOLLAR -> electronicCurrency.usd_to_dzd_sell.toBigDecimal()
                .setScale(2)
        }
    )

    val maxValue by rememberUpdatedState(
        newValue =
        when (selectedTab) {
            ElectronicCurrencyTab.EURO -> electronicCurrency.eur_to_dzd_buy.toBigDecimal()
                .setScale(2)

            ElectronicCurrencyTab.DOLLAR -> electronicCurrency.usd_to_dzd_buy.toBigDecimal()
                .setScale(2)
        }
    )


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 22.5.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (electronicCurrency.icon != null) {
                Image(
                    painter = painterResource(electronicCurrency.icon),
                    contentDescription = null,

                    modifier = Modifier
                        .size(48.dp)
                        .padding(
                            vertical = 4.5.dp,
                        )
                        .padding(end = 10.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = electronicCurrency.currency_name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = selectedTab.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Min DZD",
                    modifier = Modifier,
                    fontSize = 15.sp,
                )
                Text(
                    text = minValue.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(0.48f)
                    .padding(horizontal = 6.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
            Column(
                modifier = Modifier
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Max DZD",
                    modifier = Modifier,
                    fontSize = 15.sp,
                )
                Text(
                    text = maxValue.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }
    }

}


@Preview
@Composable
private fun ElectronicCurrencyPreview() {
    ChangeDinarTheme {
        ElectronicCurrencyItem(
            selectedTab = ElectronicCurrencyTab.EURO,
            electronicCurrency = ElectronicCurrency(
                id = 1,
                currency_id = 1,
                icon = R.drawable.ic_paypal,
                currency_name = "Dinar",
                eur_to_dzd_sell = 1.0,
                eur_to_dzd_buy = 1.2,
                usd_to_dzd_sell = 1.0,
                usd_to_dzd_buy = 1.2,
                recorded_at = Date()
            )
        )
    }

}