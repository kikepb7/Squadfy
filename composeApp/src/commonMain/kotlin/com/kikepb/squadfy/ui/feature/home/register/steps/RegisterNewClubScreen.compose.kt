package com.kikepb.squadfy.ui.feature.home.register.steps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kikepb.squadfy.ui.common.components.FormItem
import com.kikepb.squadfy.ui.common.components.RegisterStepScaffold
import com.kikepb.squadfy.ui.feature.home.register.RegisterState

@Composable
fun RegisterNewClubScreenView(
    state: RegisterState,
    onClubNameChanged: (String) -> Unit,
    onInviteCodeChanged: (String) -> Unit,
    onNext: (String, String) -> Unit,
    onBack: () -> Unit
) {
    val formState = state as? RegisterState.Form

    val clubName = formState?.clubToCreate?.name.orEmpty()
    val inviteCode = formState?.clubMember?.inviteCode.orEmpty()

    RegisterStepScaffold(
        onBack = onBack,
        content = {
            FormItem(
                value = clubName,
                onValueChange = onClubNameChanged,
                label = "Nombre del club",
                itemIcon = Icons.Default.SportsSoccer,
                modifier = Modifier.fillMaxWidth()
            )

            FormItem(
                value = inviteCode,
                onValueChange = { onInviteCodeChanged(it.uppercase()) },
                label = "Código de invitación",
                itemIcon = Icons.Default.Password,
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomContent = {
            Button(
                onClick = { onNext(clubName, inviteCode) },
                enabled = clubName.isNotBlank() && inviteCode.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar Club")
            }
        }
    )
}
