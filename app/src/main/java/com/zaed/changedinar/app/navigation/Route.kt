package com.zaed.changedinar.app.navigation

import kotlinx.serialization.Serializable

enum class  Route(val routeName:String) {
    CRYPTO_SCREEN("crypto_screen"),
    ELECTRONIC_SCREEN("electronic_screen"),
    CURRENCIES_SCREEN("currencies_screen"),
    CONVERTER_SCREEN("converter_screen")

}