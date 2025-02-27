package com.zaed.changedinar.app.navigation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zaed.changedinar.R

@Composable
fun BottomNavigation(navController: NavController, modifier: Modifier) {
    val items = listOf(
        BottomNavItem(
            title = "Currencies",
            route = Route.CURRENCIES_SCREEN.routeName,
            icon = R.drawable.ic_currency // Add this icon resource
        ),
        BottomNavItem(
            title = "Converter",
            route = Route.CONVERTER_SCREEN.routeName,
            icon = R.drawable.calculate_amount_image // Add this icon resource
        ),
        BottomNavItem(
            title = "Electronics",
            route = Route.ELECTRONIC_SCREEN.routeName,
            icon = R.drawable.ic_num_money // Add this icon resource
        ),
        BottomNavItem(
            title = "Crypto",
            route = Route.CRYPTO_SCREEN.routeName,
            icon = R.drawable.ic_crypto // Add this icon resource
        ),
    )

    NavigationBar(
        tonalElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth().padding(16.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.8f)),
        containerColor = Color.Transparent,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        items.forEach { item ->
            val selected = currentRoute == item.route.toString()
            
            NavigationBarItem(
                icon = { 
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    if (selected) {
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

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Int
)