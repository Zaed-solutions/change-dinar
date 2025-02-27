package com.zaed.changedinar.data.model

import androidx.annotation.DrawableRes
import com.zaed.changedinar.R
import kotlinx.serialization.Serializable

@Serializable
data class ElectronicCurrencyEntity(
    val id: Int,
    val currency_id: Int,
    val currency_name: String,
    val eur_to_dzd_sell: String,
    val eur_to_dzd_buy: String,
    val usd_to_dzd_sell: String,
    val usd_to_dzd_buy: String,
    val recorded_at: String
)

enum class ElectronicCurrencyIcon(val id: Int, @DrawableRes val iconRes: Int) {
    PAYPAL(1, R.drawable.ic_paypal),
    WISE(2, R.drawable.ic_wise),
    PAYSERA(4, R.drawable.ic_paysera),
    REDOTPAY(5, R.drawable.ic_redot),
    N26(6, R.drawable.ic_n26),
    MYFIN(7, R.drawable.ic_my_fin),
    PAYONEER(8, R.drawable.ic_payoneer);

    companion object {
        fun fromId(id: Int): ElectronicCurrencyIcon? {
            return ElectronicCurrencyIcon.entries.find { it.id == id }
        }
    }
}