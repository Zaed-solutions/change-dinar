package com.zaed.changedinar.data.model

import androidx.annotation.DrawableRes
import com.zaed.changedinar.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@kotlinx.serialization.Serializable
data class CryptoEntity(
    val crypto_code: String = "", //dogecoin
    val price_in_usd: String = "", //0.210747
    val recorded_at: String = "", //2025-02-26T15:50:00.968
)

fun CryptoEntity.toCrypto(): CryptoModel {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
    return CryptoModel(
        crypto_code = CryptoCode.fromString(crypto_code),
        price_in_usd = price_in_usd.toDoubleOrNull() ?: 0.0,
        recorded_at = dateFormat.parse(recorded_at) ?: Date(),
    )
}

enum class CryptoCode(
    val code: String,
    val title: String = "",
    @DrawableRes val icon: Int = R.drawable.bnb_icon,
) {
    DOGE(code = "dogecoin", title = "Doge", icon = R.drawable.doge_icon),
    BITCOIN(code = "bitcoin", title = "Bitcoin", icon = R.drawable.bitcoin_icon),
    ETHEREUM(code = "ethereum", title = "Ethereum", icon = R.drawable.ethereum_icon),
    SOLANA(code = "solana", title = "Solana", icon = R.drawable.solana_icon),
    BINANCE(code = "binancecoin", title = "Binance Coin", icon = R.drawable.bnb_icon),
    RIPPLE(code = "ripple", title = "Ripple", icon = R.drawable.ripple_icon),
    CARDANO(code = "cardano", title = "Cardano", icon = R.drawable.cardano_icon),
    POLKADOT(code = "polkadot", title = "Polkadot", icon = R.drawable.polkadot_icon),
    SHIBA_INU(code = "shiba-inu", title = "Shiba Inu", icon = R.drawable.shiba_icon),
    PEPE(code = "pepe", title = "Pepe", icon = R.drawable.pepe_icon),
    AVALANCHE_2(code = "avalanche-2", title ="Avalanche" , icon = R.drawable.avalanche_icon ),
    LITECOIN(code = "litecoin", title = "Litecoin", icon = R.drawable.lite_icon),
    TETHER(code = "tether", title = "Tether", icon = R.drawable.tether_icon),
    UNKOWNN(code = "unknown", title = "Unknown");

    companion object {
        fun fromString(code: String): CryptoCode {
            return CryptoCode.entries.find { it.code == code }
                ?: UNKOWNN
        }
    }
}


