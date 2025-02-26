package com.zaed.changedinar.data.model

import java.util.Date

data class CryptoModel(
    val crypto_code: CryptoCode=CryptoCode.UNKOWNN, //dogecoin
    val price_in_usd: Double=0.0, //0.210747
    val recorded_at: Date=Date(), //2025-02-26T15:50:00.968
)