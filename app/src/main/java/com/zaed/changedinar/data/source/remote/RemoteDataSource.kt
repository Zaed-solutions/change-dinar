package com.zaed.changedinar.data.source.remote

import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.data.model.Currency

interface RemoteDataSource{
    suspend fun fetchCrypto():Result<List<CryptoModel>>
    suspend fun fetchCurrencies(): Result<List<Currency>>
}