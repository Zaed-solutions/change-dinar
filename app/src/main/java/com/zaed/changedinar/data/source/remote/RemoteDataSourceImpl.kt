package com.zaed.changedinar.data.source.remote

import android.util.Log
import com.zaed.changedinar.data.model.CryptoEntity
import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.data.mapper.toCrypto
import com.zaed.changedinar.data.mapper.toElectronicCurrency
import com.zaed.changedinar.data.model.ElectronicCurrency
import com.zaed.changedinar.data.model.ElectronicCurrencyEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {
    private val cryptoBaseUrl = "https://dzexchange-production.up.railway.app/api/v1/crypto"
    private val electronicBaseUrl = "https://dzexchange-production.up.railway.app/api/v1/electronic-currencies/latest"
    override suspend fun fetchCrypto(): Result<List<CryptoModel>> {
        try {
            val response = httpClient.get {
                url(cryptoBaseUrl)
                contentType(ContentType.Application.Json)
            }
            if(response.status.value in 200..299){
                Log.d("TAG", "fetchCrypto: ${response.body<List<CryptoEntity>>()}")
                val result = response.body<List<CryptoEntity>>().map { it.toCrypto() }
                Log.d("TAG", "fetchCrypto: $result")
                return Result.success(result)
            }else{
                return Result.failure(Exception("Error: ${response.status.value}"))
            }
        }catch (e:Exception){
            return Result.failure(e)
        }
    }

    override suspend fun fetchElectronic(): Result<List<ElectronicCurrency>> {
        try {
            val response = httpClient.get {
                url(electronicBaseUrl)
                contentType(ContentType.Application.Json)
            }
            if(response.status.value in 200..299){
                Log.d("TAG", "fetchElectronics: ${response.body<List<ElectronicCurrencyEntity>>()}")
                val result = response.body<List<ElectronicCurrencyEntity>>().map { it.toElectronicCurrency() }
                Log.d("TAG", "fetchElectronics: $result")
                return Result.success(result)
            }else{
                return Result.failure(Exception("Error: ${response.status.value}"))
            }
        }catch (e:Exception){
            return Result.failure(e)
        }    }
}