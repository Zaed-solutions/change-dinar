package com.zaed.changedinar.ui

import android.Manifest
import android.graphics.Color.TRANSPARENT
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.zaed.changedinar.app.navigation.BottomNavigation
import com.zaed.changedinar.app.navigation.NavigationHost
import com.zaed.changedinar.ui.crypto.component.linearBackground
import com.zaed.changedinar.ui.theme.ChangeDinarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission()
        val lightTransparentStyle = SystemBarStyle.light(
            scrim = TRANSPARENT,
            darkScrim = TRANSPARENT
        )
        enableEdgeToEdge(
            statusBarStyle = lightTransparentStyle,
            navigationBarStyle = lightTransparentStyle
        )
        setContent {
            ChangeDinarTheme {
                App()
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .linearBackground()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),

            containerColor = Color.Transparent,
            contentColor = LocalContentColor.current,

            ) { paddingValues ->
            NavigationHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
            )
        }
        BottomNavigation(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )

    }
}