package com.kikepb.squadfy.ui.common.navigation

sealed class Routes(val route: String) {
    data object Home: Routes("home")

    // Login
    data object LoginGraph : Routes("login_graph")
    data object Login : Routes("login")

    // Register
    data object RegisterGraph : Routes("register_graph")
    data object RegisterEmail : Routes("register/email")
    data object RegisterPassword : Routes("register/password")
    data object RegisterProfile : Routes("register/profile")
    data object RegisterClubSelector : Routes("register/club_selector")
    data object RegisterClubInvitationCode : Routes("register/club_invite")
    data object RegisterNewClub : Routes("register/new_club")
    data object RegisterFinish : Routes("register/finish/{userId}/{clubId}") {
        fun createRoute(userId: String, clubId: String) = "register/finish/$userId/$clubId"
    }
}