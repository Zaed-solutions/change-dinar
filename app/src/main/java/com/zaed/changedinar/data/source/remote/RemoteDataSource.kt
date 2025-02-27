package com.zaed.changedinar.data.source.remote

import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.data.model.ElectronicCurrency

interface RemoteDataSource{
    suspend fun fetchCrypto():Result<List<CryptoModel>>
    suspend fun fetchElectronic():Result<List<ElectronicCurrency>>

}