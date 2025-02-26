package com.zaed.changedinar.data.source.remote

import com.zaed.changedinar.data.model.CryptoModel

interface RemoteDataSource{
    suspend fun fetchCrypto():Result<List<CryptoModel>>
}