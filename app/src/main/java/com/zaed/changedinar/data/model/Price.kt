package com.zaed.changedinar.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Price(
    val date: String,
    val official_price: String,
    val unofficial_buy_price: String,
    val unofficial_sell_price: String
)