package com.kikepb.squadfy.ui.feature.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kikepb.squadfy.ui.common.components.OutlinedAppButton
import com.kikepb.squadfy.ui.common.components.PrimaryButton

@Composable
fun RegistrationRoute(
    onSignUpClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
//    NotificationPermissionRequest()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a MATCHDAY",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        PrimaryButton(
            text = "Iniciar sesión",
            onClick = onSignUpClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedAppButton(
            text = "Registrarse",
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}