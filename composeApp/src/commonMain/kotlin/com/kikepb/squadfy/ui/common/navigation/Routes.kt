package com.kikepb.squadfy.ui.common.navigation

sealed class Routes(val route: String) {
    data object Home: Routes("home")
}