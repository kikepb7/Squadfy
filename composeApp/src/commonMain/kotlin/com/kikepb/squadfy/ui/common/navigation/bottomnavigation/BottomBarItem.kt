package com.kikepb.squadfy.ui.common.navigation.bottomnavigation

import androidx.compose.runtime.Composable

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit
}