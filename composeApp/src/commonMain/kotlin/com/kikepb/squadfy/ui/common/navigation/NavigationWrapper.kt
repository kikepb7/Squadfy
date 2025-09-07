package com.kikepb.squadfy.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.kikepb.squadfy.ui.common.navigation.Routes.Home
import com.kikepb.squadfy.ui.common.navigation.Routes.Login
import com.kikepb.squadfy.ui.common.navigation.Routes.LoginGraph
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterClubInvitationCode
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterClubSelector
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterEmail
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterFinish
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterGraph
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterNewClub
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterPassword
import com.kikepb.squadfy.ui.common.navigation.Routes.RegisterProfile
import com.kikepb.squadfy.ui.feature.home.HomeRoute
import com.kikepb.squadfy.ui.feature.home.login.LoginRoute
import com.kikepb.squadfy.ui.feature.home.register.RegisterStepViewModel
import com.kikepb.squadfy.ui.feature.home.register.steps.RegisterClubInvitationCodeScreenView
import com.kikepb.squadfy.ui.feature.home.register.steps.RegisterClubSelectorScreenView
import com.kikepb.squadfy.ui.feature.home.register.steps.RegisterFinishStepScreenView
import com.kikepb.squadfy.ui.feature.home.register.steps.RegisterNewClubScreenView
import com.kikepb.squadfy.ui.feature.home.register.steps.RegisterProfileScreenView
import com.kikepb.squadfy.ui.feature.home.register.steps.RegisterSetPasswordScreenView
import com.kikepb.squadfy.ui.feature.home.register.steps.RegisterWithEmailScreenView
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationWrapper(modifier: Modifier = Modifier) {
    val mainNavController = rememberNavController()
    val registerStepViewModel = koinViewModel<RegisterStepViewModel>()
    val registerState by registerStepViewModel.state.collectAsStateWithLifecycle()

    NavHost(
        navController = mainNavController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        // --- HOME ---
        composable(route = Home.route) {
            HomeRoute(
                onSignUpClick = { mainNavController.navigate(Login.route) },
                onRegisterClick = { mainNavController.navigate(RegisterEmail.route) }
            )
        }

        // --- LOGIN GRAPH ---
        navigation(
            startDestination = Login.route,
            route = LoginGraph.route
        ) {
            composable(Login.route) {
                LoginRoute(
                    onSignUpClick = { _, _ -> },
                    onRegisterClick = { mainNavController.navigate(RegisterGraph.route)}
                )
            }
        }

        // --- REGISTER GRAPH ---
        navigation(
            startDestination = RegisterEmail.route,
            route = RegisterGraph.route
        ) {
            composable(RegisterEmail.route) {
                val viewModel = koinViewModel<RegisterStepViewModel>()

                RegisterWithEmailScreenView(
                    state = registerState,
                    onNext = { mainNavController.navigate(RegisterPassword.route) },
                    onBack = { mainNavController.popBackStack() },
                    onEmailChanged = viewModel::onEmailChanged
                )
            }

            composable(RegisterPassword.route) {
                val viewModel = koinViewModel<RegisterStepViewModel>()

                RegisterSetPasswordScreenView(
                    state = registerState,
                    onPasswordChanged = viewModel::onPasswordChanged,
                    onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
                    onNext = { mainNavController.navigate(RegisterProfile.route) },
                    onBack = { mainNavController.popBackStack() }
                )
            }

            composable(RegisterProfile.route) {
                val viewModel = koinViewModel<RegisterStepViewModel>()

                RegisterProfileScreenView(
                    state = registerState,
                    onNameChanged = viewModel::onNameChanged,
                    onLastNameChanged = viewModel::onLastNameChanged,
                    onPhoneNumberChanged = viewModel::onPhoneNumberChanged,
                    onPositionChanged = viewModel::onPositionChanged,
                    onNumberChanged = viewModel::onNumberChanged,
                    onRoleChanged = viewModel::onRoleChanged,
                    onUploadImage = { uri -> viewModel.uploadImage(uri) },
                    onNavigateToClubSelector = { mainNavController.navigate(RegisterClubSelector.route) },
                    onNavigateToInvitationCode = { mainNavController.navigate(RegisterClubInvitationCode.route) },
                    onBack = { mainNavController.popBackStack() }
                )
            }

            composable(RegisterClubSelector.route) {
                RegisterClubSelectorScreenView(
                    state = registerState,
                    onCreateNewClub = { mainNavController.navigate(RegisterNewClub.route) },
                    onUseInviteCode = { mainNavController.navigate(RegisterClubInvitationCode.route) },
                    onBack = { mainNavController.popBackStack() }
                )
            }

            composable(RegisterClubInvitationCode.route) {
                val viewModel = koinViewModel<RegisterStepViewModel>()

                RegisterClubInvitationCodeScreenView(
                    state = registerState,
                    onInviteCodeChanged = viewModel::onInviteCodeChanged,
                    onRegisterSubmit = { viewModel.onRegisterSubmit() },
                    onFinish = { userId, clubId ->
                        mainNavController.navigate(RegisterFinish.createRoute(userId, clubId))
                    },
                    onBack = { mainNavController.popBackStack() }
                )
            }

            composable(RegisterNewClub.route) {
                val viewModel = koinViewModel<RegisterStepViewModel>()

                RegisterNewClubScreenView(
                    state = registerState,
                    onClubNameChanged = viewModel::onClubNameChanged,
                    onInviteCodeChanged = { code -> viewModel.onInviteCodeChanged(code.uppercase()) },
                    onNext = { userId, clubId ->
                        mainNavController.navigate(RegisterFinish.createRoute(userId, clubId))
                    },
                    onBack = { mainNavController.popBackStack() }
                )
            }

            composable(RegisterFinish.route) { backStackEntry ->
                val route = backStackEntry.destination.route ?: ""
                val parts = route.split("/")
                val userId = parts.getOrNull(2) ?: ""
                val clubId = parts.getOrNull(3) ?: ""

                RegisterFinishStepScreenView(
                    userId = userId,
                    clubId = clubId,
                    onFinish = { uId, cId ->
                        mainNavController.navigate(Home.route) {
                            popUpTo(Home.route) { inclusive = true }
                        }
                    },
                    onBack = { mainNavController.popBackStack() }
                )
            }
        }
    }
}