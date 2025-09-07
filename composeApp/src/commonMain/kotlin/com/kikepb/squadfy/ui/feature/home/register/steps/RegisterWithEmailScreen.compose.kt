package com.kikepb.squadfy.ui.feature.home.register.steps

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.kikepb.squadfy.ui.feature.home.register.RegisterState
import com.kikepb.squadfy.ui.common.components.FormItem
import com.kikepb.squadfy.ui.common.components.PrimaryButton
import com.kikepb.squadfy.ui.common.components.RegisterStepScaffold
import com.kikepb.squadfy.ui.common.theme.PrimaryTextColor
import com.kikepb.squadfy.ui.common.theme.SecondaryTextColor

@Composable
fun RegisterWithEmailScreenView(
    state: RegisterState,
    onNext: () -> Unit,
    onBack: () -> Unit,
    onEmailChanged: (String) -> Unit
) {
    val formState = state as? RegisterState.Form
    val email = formState?.clubMember?.user?.email.orEmpty()

    RegisterStepScaffold(
        onBack = onBack,
        content = {
            Text(
                text = "¿Cuál es tu correo electrónico?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = PrimaryTextColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Introduce tu correo electrónico de contacto. Nadie lo verá en tu perfil",
                style = MaterialTheme.typography.bodyMedium,
                color = SecondaryTextColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            FormItem(
                value = email,
                onValueChange = onEmailChanged,
                label = "Correo electrónico",
                itemIcon = Icons.Default.Email,
                isRequired = true
            )
        },
        bottomContent = {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Siguiente",
                onClick = onNext
            )
        }
    )
}