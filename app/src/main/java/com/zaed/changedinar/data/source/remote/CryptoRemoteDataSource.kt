package com.zaed.changedinar.data.source.remote

import com.zaed.changedinar.data.model.CryptoModel

interface CryptoRemoteDataSource{
    suspend fun fetchCrypto():Result<List<CryptoModel>>
}