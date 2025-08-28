package com.kikepb.squadfy.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kikepb.squadfy.ui.common.navigation.Routes.Home

@Composable
fun NavigationWrapper(modifier: Modifier = Modifier) {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Home.route) {
        composable(route = Home.route) {  }
    }
}