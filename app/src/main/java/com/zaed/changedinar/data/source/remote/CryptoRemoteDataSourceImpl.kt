package com.zaed.changedinar.data.source.remote

import android.util.Log
import com.zaed.changedinar.data.model.CryptoEntity
import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.data.model.toCrypto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CryptoRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : CryptoRemoteDataSource {
    private val baseUrl = "https://dzexchange-production.up.railway.app/api/v1/crypto"
    override suspend fun fetchCrypto(): Result<List<CryptoModel>> {
        try {
            val response = httpClient.get {
                url(baseUrl)
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
}