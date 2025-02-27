package com.zaed.changedinar.data.model

@kotlinx.serialization.Serializable
data class CryptoEntity(
    val crypto_code: String = "", //dogecoin
    val price_in_usd: String = "", //0.210747
    val recorded_at: String = "", //2025-02-26T15:50:00.968
)


