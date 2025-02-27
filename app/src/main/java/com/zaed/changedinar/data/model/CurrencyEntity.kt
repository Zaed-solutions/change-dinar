package com.zaed.changedinar.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyEntity(
    val code: String,
    val id: Int,
    val name: String,
    val price: Price,
    val symbol: String
)


