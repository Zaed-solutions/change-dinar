package com.zaed.changedinar.data.mapper

import com.zaed.changedinar.data.model.ElectronicCurrency
import com.zaed.changedinar.data.model.ElectronicCurrencyEntity
import com.zaed.changedinar.data.model.ElectronicCurrencyIcon

fun ElectronicCurrencyEntity.toElectronicCurrency() =
    ElectronicCurrency(
        id = id,
        currency_id = currency_id,
        icon = ElectronicCurrencyIcon.fromId(currency_id)?.iconRes,
        currency_name = currency_name,
        eur_to_dzd_sell = eur_to_dzd_sell.toDouble(),
        eur_to_dzd_buy = eur_to_dzd_buy.toDouble(),
        usd_to_dzd_sell = usd_to_dzd_sell.toDouble(),
        usd_to_dzd_buy = usd_to_dzd_buy.toDouble(),
        recorded_at = recorded_at.toDate()
    )