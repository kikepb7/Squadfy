package com.kikepb.squadfy.ui.feature.home.register.steps

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kikepb.squadfy.ui.feature.home.register.RegisterState
import com.kikepb.squadfy.ui.common.components.FormItem
import com.kikepb.squadfy.ui.common.components.RegisterStepScaffold
import com.kikepb.squadfy.ui.common.theme.PasswordRuleFailedColor
import com.kikepb.squadfy.ui.common.theme.PasswordRulePassedColor

@Composable
fun RegisterSetPasswordScreenView(
    state: RegisterState,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onNext: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val formState = state as? RegisterState.Form

    val password = formState?.clubMember?.user?.password.orEmpty()
    val confirmPassword = formState?.confirmPassword.orEmpty()
    val rules = formState?.passwordRules.orEmpty()
    val doPasswordsMatch = formState?.doPasswordsMatch == true
    val isFormValid = formState?.isPasswordValid == true && doPasswordsMatch

    RegisterStepScaffold(
        onBack = onBack,
        content = {
            Text(
                text = "Establece tu contraseña",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Crea una contraseña segura para tu cuenta. Debe cumplir con los siguientes requisitos:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            rules.forEach { (rule, passed) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (passed) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        tint = if (passed) PasswordRulePassedColor else PasswordRuleFailedColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = rule,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (passed) PasswordRulePassedColor else PasswordRuleFailedColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            FormItem(
                value = password,
                onValueChange = {
                    if (it.length <= 8) onPasswordChanged(it)
                },
                label = "Contraseña",
                itemIcon = Icons.Default.Lock,
                isPassword = true,
                isRequired = true
            )

            FormItem(
                value = formState?.confirmPassword.orEmpty(),
                onValueChange = {
                    if (it.length <= 8) onConfirmPasswordChanged(it)
                },
                label = "Confirmar contraseña",
                itemIcon = Icons.Default.Lock,
                isPassword = true,
                isRequired = true
            )

            if (confirmPassword.isNotEmpty() && !doPasswordsMatch) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Las contraseñas no coinciden",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        },
        bottomContent = {
            Button(
                onClick = onNext,
                enabled = isFormValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Siguiente")
            }
        }
    )
}