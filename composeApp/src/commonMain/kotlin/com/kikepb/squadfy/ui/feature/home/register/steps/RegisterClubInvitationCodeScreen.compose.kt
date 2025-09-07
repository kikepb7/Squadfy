package com.kikepb.squadfy.ui.feature.home.register.steps

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kikepb.squadfy.ui.common.components.FormItem
import com.kikepb.squadfy.ui.common.components.RegisterStepScaffold
import com.kikepb.squadfy.ui.feature.home.register.RegisterState

@Composable
fun RegisterClubInvitationCodeScreenView(
    state: RegisterState,
    onInviteCodeChanged: (String) -> Unit,
    onRegisterSubmit: () -> Unit,
    onFinish: (userId: String, clubId: String) -> Unit,
    onBack: () -> Unit
) {
    val formState = state as? RegisterState.Form
    val inviteCode = formState?.clubMember?.inviteCode.orEmpty()

    LaunchedEffect(state) {
        if (state is RegisterState.Success) {
            onFinish(state.user.id ?: "", state.club?.id ?: "")
        }
    }

    RegisterStepScaffold(
        onBack = onBack,
        content = {
            Text(text = "Introduce el código de invitación del club")

            Spacer(modifier = Modifier.height(16.dp))

            FormItem(
                value = inviteCode,
                onValueChange = onInviteCodeChanged,
                label = "Código de invitación",
                itemIcon = Icons.Default.Password
            )
        },
        bottomContent = {
            Button(
                onClick = onRegisterSubmit,
                enabled = inviteCode.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar registro")
            }
        }
    )
}