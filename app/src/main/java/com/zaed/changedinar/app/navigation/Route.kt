package com.zaed.changedinar.app.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object DefaultRoute : Route
}