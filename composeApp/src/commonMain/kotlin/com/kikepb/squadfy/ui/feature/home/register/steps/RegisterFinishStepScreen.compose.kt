package com.kikepb.squadfy.ui.feature.home.register.steps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kikepb.squadfy.ui.common.components.PrimaryButton
import com.kikepb.squadfy.ui.common.components.RegisterStepScaffold
import com.kikepb.squadfy.ui.common.theme.PrimarySuccessColor
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterFinishStepScreenView(
    userId: String,
    clubId: String,
    onFinish: (String, String) -> Unit,
    onBack: () -> Unit
) {
    RegisterStepScaffold(
        onBack = onBack,
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Registro completado",
                    tint = PrimarySuccessColor,
                    modifier = Modifier
                        .size(96.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "¡Bienvenido a la app!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tu cuenta ha sido creada con éxito y ahora puedes comenzar a disfrutar de todas las funcionalidades que la aplicación tiene preparadas para ti.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        },
        bottomContent = {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Ir al Dashboard",
                onClick = { onFinish(userId, clubId) }
            )
        }
    )
}

@Preview
@Composable
fun RegisterFinishStepScreenViewPreview() {
    RegisterFinishStepScreenView(
        userId = "",
        clubId = "",
        onFinish = { _, _ -> },
        onBack = {}
    )
}