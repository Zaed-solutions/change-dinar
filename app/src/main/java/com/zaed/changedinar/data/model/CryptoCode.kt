package com.zaed.changedinar.data.model

import androidx.annotation.DrawableRes
import com.zaed.changedinar.R

enum class CryptoCode(
    val code: String,
    val title: String = "",
    @DrawableRes val icon: Int = R.drawable.ic_bnb,
) {
    DOGE(code = "dogecoin", title = "Doge", icon = R.drawable.ic_doge),
    BITCOIN(code = "bitcoin", title = "Bitcoin", icon = R.drawable.ic_bitcoin),
    ETHEREUM(code = "ethereum", title = "Ethereum", icon = R.drawable.ic_ethereum),
    SOLANA(code = "solana", title = "Solana", icon = R.drawable.ic_solana),
    BINANCE(code = "binancecoin", title = "Binance Coin", icon = R.drawable.ic_bnb),
    RIPPLE(code = "ripple", title = "XRP", icon = R.drawable.ic_ripple),
    CARDANO(code = "cardano", title = "Cardano", icon = R.drawable.ic_cardano),
    POLKADOT(code = "polkadot", title = "Polkadot", icon = R.drawable.ic_polkadot),
    SHIBA_INU(code = "shiba-inu", title = "Shiba Inu", icon = R.drawable.ic_shiba),
    PEPE(code = "pepe", title = "Pepe", icon = R.drawable.ic_pepe),
    AVALANCHE_2(code = "avalanche-2", title ="Avalanche" , icon = R.drawable.ic_avalanche),
    LITECOIN(code = "litecoin", title = "Litecoin", icon = R.drawable.ic_lite),
    TETHER(code = "tether", title = "Tether", icon = R.drawable.ic_tether),
    UNKOWNN(code = "unknown", title = "Unknown");

    companion object {
        fun fromString(code: String): CryptoCode {
            return CryptoCode.entries.find { it.code == code }
                ?: UNKOWNN
        }
    }
}