package com.zaed.changedinar.data.mapper

import com.zaed.changedinar.data.model.CryptoCode
import com.zaed.changedinar.data.model.CryptoEntity
import com.zaed.changedinar.data.model.CryptoModel

fun CryptoEntity.toCrypto() =
    CryptoModel(
        crypto_code = CryptoCode.fromString(crypto_code),
        price_in_usd = price_in_usd.toDoubleOrNull() ?: 0.0,
        recorded_at = recorded_at.toDate(),
    )