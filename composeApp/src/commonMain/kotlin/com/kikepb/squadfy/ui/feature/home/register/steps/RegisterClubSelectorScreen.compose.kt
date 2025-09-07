package com.kikepb.squadfy.ui.feature.home.register.steps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kikepb.squadfy.ui.common.components.RegisterStepScaffold
import com.kikepb.squadfy.ui.feature.home.register.RegisterState

@Composable
fun RegisterClubSelectorScreenView(
    state: RegisterState,
    onCreateNewClub: () -> Unit,
    onUseInviteCode: () -> Unit,
    onBack: () -> Unit
) {
    RegisterStepScaffold(
        onBack = onBack,
        content = {
            Text(
                "Selecciona una opción para continuar",
                style = MaterialTheme.typography.bodyMedium
            )

            Button(
                onClick = onCreateNewClub,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Crear un nuevo club")
            }

            Button(
                onClick = onUseInviteCode,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Unirme a un club con código")
            }
        },
        bottomContent = {}
    )
}