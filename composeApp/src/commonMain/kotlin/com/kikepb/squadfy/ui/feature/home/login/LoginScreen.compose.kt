package com.kikepb.squadfy.ui.feature.home.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kikepb.squadfy.ui.common.components.BottomSheetErrorView
import com.kikepb.squadfy.ui.common.components.FormItem
import com.kikepb.squadfy.ui.common.components.LinkTextButton
import com.kikepb.squadfy.ui.common.components.OutlinedAppButton
import com.kikepb.squadfy.ui.common.components.PrimaryButton
import com.kikepb.squadfy.ui.common.extensions.UiError
import com.kikepb.squadfy.ui.common.extensions.toUiError
import com.kikepb.squadfy.ui.common.theme.BackgroundAppColor
import com.kikepb.squadfy.ui.common.theme.PrimaryTextColor
import com.kikepb.squadfy.ui.common.theme.SecondaryTextColor
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRoute(
    onSignUpClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {
    val loginViewModel = koinViewModel<LoginViewModel>()
    val signUpState by loginViewModel.state.collectAsStateWithLifecycle()

//    val googleSignIn = rememberGoogleSignIn { idToken ->
//        signUpViewModel.registerWithGoogle(idToken)
//    }

    LoginScreenView(
        state = signUpState,
        onEmailSignIn = { email, password ->
            loginViewModel.signUpWithEmail(email = email, password = password)
        },
        onGoogleSignIn = { }, // googleSignIn()
        onRegisterClick = onRegisterClick,
        onSignUpClick = onSignUpClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenView(
    state: SignUpState,
    onEmailSignIn: (String, String) -> Unit,
    onGoogleSignIn: () -> Unit,
    onRegisterClick: () -> Unit,
    onSignUpClick: (String, String) -> Unit
) {
    var showError by remember { mutableStateOf(false) }
    var uiError by remember { mutableStateOf(UiError(message = "")) }

    LaunchedEffect(key1 = state) {
        if (state is SignUpState.Error) {
            uiError = state.failure.toUiError()
            showError = true
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(BackgroundAppColor)) {
        when (state) {
            is SignUpState.Loading -> CircularProgressIndicator()
//            CustomLoaderView(animationResId = R.draw .match_day_loader, message = "Cargando ...")

            is SignUpState.Success -> {
                LaunchedEffect(Unit) {
                    onSignUpClick(state.userId, state.clubId)
                }
            }

            else -> {
                LoginFormContent(
                    onEmailSignIn = onEmailSignIn,
                    onGoogleSignIn = onGoogleSignIn,
                    onRegisterClick = onRegisterClick
                )
            }
        }

        BottomSheetErrorView(
            visible = showError,
            title = uiError.title,
            description = uiError.message,
            imageRes = uiError.image,
            onDismiss = { showError = false }
        )
    }
}


@Composable
fun LoginFormContent(
    onEmailSignIn: (String, String) -> Unit,
    onGoogleSignIn: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundAppColor)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = PrimaryTextColor
        )

        Spacer(Modifier.height(24.dp))

        FormItem(
            value = email,
            onValueChange = { email = it },
            label = "Correo electrónico",
            itemIcon = Icons.Default.Email
        )

        Spacer(Modifier.height(16.dp))

        FormItem(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña",
            itemIcon = Icons.Default.Lock,
            isPassword = true
        )

        Spacer(Modifier.height(24.dp))

        PrimaryButton(
            text = "Iniciar sesión",
            onClick = { onEmailSignIn(email, password) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedAppButton(
            text = "Registrarse",
            onClick = onGoogleSignIn,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        LinkTextButton(
            text = "¿No tienes cuenta? Regístrate",
            onClick = onRegisterClick,
            color = SecondaryTextColor
        )
    }
}