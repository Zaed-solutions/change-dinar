package com.zaed.changedinar.data.model

import androidx.annotation.DrawableRes
import java.util.Date

data class ElectronicCurrency(
    val id: Int,
    val currency_id: Int,
    @DrawableRes val icon: Int?,
    val currency_name: String,
    val eur_to_dzd_sell: Double,
    val eur_to_dzd_buy: Double,
    val usd_to_dzd_sell: Double,
    val usd_to_dzd_buy: Double,
    val recorded_at: Date
)