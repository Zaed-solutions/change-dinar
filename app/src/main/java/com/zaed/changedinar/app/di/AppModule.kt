package com.zaed.changedinar.app.di

import com.zaed.changedinar.data.source.remote.RemoteDataSource
import com.zaed.changedinar.data.source.remote.RemoteDataSourceImpl
import com.zaed.changedinar.ui.crypto.CryptoViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single<HttpClient>{
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    isLenient = true
                    encodeDefaults = true
                })
            }
        }
    }
    singleOf(::RemoteDataSourceImpl) { bind<RemoteDataSource>() }
    viewModelOf(::CryptoViewModel)
}

