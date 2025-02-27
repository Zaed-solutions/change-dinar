package com.zaed.changedinar.app.navigation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zaed.changedinar.ui.converter.ConverterScreen
import com.zaed.changedinar.ui.crypto.CryptoScreen
import com.zaed.changedinar.ui.electronic.ElectronicsScreen
import com.zaed.changedinar.ui.currencies.CurrenciesScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.CURRENCIES_SCREEN.routeName,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    500, easing = LinearEasing
                )
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    500, easing = LinearEasing
                )
            )
        }
    ) {
        composable(Route.CRYPTO_SCREEN.routeName) {
            CryptoScreen()
        }
        composable(Route.CURRENCIES_SCREEN.routeName) {
            CurrenciesScreen()
        }
        composable(Route.CONVERTER_SCREEN.routeName) {
            ConverterScreen()
        }
        composable(Route.ELECTRONIC_SCREEN.routeName) {
            ElectronicsScreen()
        }

    }
}