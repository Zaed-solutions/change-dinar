package com.zaed.changedinar.app.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object CryptoScreen : Route
    @Serializable
    data object CurrenciesScreen : Route
    @Serializable
    data object ConverterRoute : Route
}