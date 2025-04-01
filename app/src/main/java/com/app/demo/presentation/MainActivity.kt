package com.app.demo.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.demo.navigation.api.NavigationManager
import com.app.demo.presentation.navigation.MainNavHost
import com.app.demo.ui.theme.DemoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("RestrictedApi")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainNavHost(
                navController = navController,
                navigationManager = navigationManager,
                modifier = Modifier
                    .background(color = DemoTheme.colors.surface)
                    .fillMaxSize()
                    .systemBarsPadding(),
            )
        }
    }
}
