package com.zaed.changedinar.app.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zaed.changedinar.R

@Composable
fun BottomNavigation(navController: NavController, modifier: Modifier) {
    NavigationBar(
        tonalElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.8f)),
        containerColor = Color.Transparent,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val animationSpec = tween<Float>(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
        val sizeAnimSpec = tween<IntSize>(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )

        BottomNavItem.entries.forEach { item ->
            val selected = currentRoute == item.route.toString()
            val transitionState = remember {
                MutableTransitionState(false).apply {
                    // Initialize with current selection state
                    targetState = selected
                }
            }

            LaunchedEffect(selected) {
                transitionState.targetState = selected
            }

            NavigationBarItem(
                icon = {
                    AnimatedContent(
                        targetState = selected,
                        transitionSpec = {
                            fadeIn(animationSpec = animationSpec) togetherWith
                                    fadeOut(animationSpec = animationSpec)
                        }
                    ) { state ->
                        when {
                            state -> {
                                Image(
                                    painter = painterResource(id = item.selectedIcon),
                                    contentDescription = item.title
                                )
                            }

                            else -> {
                                Image(
                                    painter = painterResource(id = item.unselectedIcon),
                                    contentDescription = item.title
                                )
                            }
                        }
                    }
                },
                label = {
                    AnimatedVisibility(
                        visibleState = transitionState,
                        enter = fadeIn(animationSpec = animationSpec) +
                                expandHorizontally(animationSpec = sizeAnimSpec),
                        exit = fadeOut(animationSpec = animationSpec) +
                                shrinkHorizontally(animationSpec = sizeAnimSpec)
                    ) {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2196F3)
                        )
                    }
                },
                selected = selected,
                onClick = {
                    if (currentRoute != item.route.toString()) {
                        navController.navigate(item.route.toString()) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White.copy(alpha = 0.6f),
                    indicatorColor = Color.White.copy(alpha = 0.1f)
                )
            )
        }
    }
}

enum class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    CURRENCIES(
        title = "Change",
        route = Route.CURRENCIES_SCREEN.routeName,
        selectedIcon = R.drawable.ic_currency_selected,
        unselectedIcon = R.drawable.ic_currency_unselected
    ),
    CONVERTER(
        title = "Converter",
        route = Route.CONVERTER_SCREEN.routeName,
        selectedIcon = R.drawable.ic_converter_selected,
        unselectedIcon = R.drawable.ic_converter_unselected
    ),
    DIGITAL(
        title = "Digital",
        route = Route.ELECTRONIC_SCREEN.routeName,
        selectedIcon = R.drawable.ic_digital_selected,
        unselectedIcon = R.drawable.ic_digital_unselected
    ),
    CRYPTO(
        title = "Crypto",
        route = Route.CRYPTO_SCREEN.routeName,
        selectedIcon = R.drawable.ic_crypto_selected,
        unselectedIcon = R.drawable.ic_crypto_unselected
    ),
}