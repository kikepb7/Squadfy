package com.kikepb.squadfy.ui.feature.home.register.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kikepb.squadfy.ui.common.components.FormItem
import com.kikepb.squadfy.ui.common.components.RegisterStepScaffold
import com.kikepb.squadfy.ui.feature.home.register.RegisterState
import com.kikepb.squadfy.utils.PlatformUri
import com.kikepb.squadfy.utils.rememberImagePicker

@Composable
fun RegisterProfileScreenView(
    state: RegisterState,
    onNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onPositionChanged: (String) -> Unit,
    onNumberChanged: (Int) -> Unit,
    onRoleChanged: (String) -> Unit,
    onUploadImage: (PlatformUri) -> Unit,
    onNavigateToClubSelector: () -> Unit,
    onNavigateToInvitationCode: () -> Unit,
    onBack: () -> Unit
) {
    val formState = state as? RegisterState.Form
    var dorsalText by remember {
        mutableStateOf(
            formState?.clubMember?.player?.number?.toString() ?: ""
        )
    }
    var positionText by remember { mutableStateOf(formState?.clubMember?.player?.position ?: "") }
    var selectedImage by remember { mutableStateOf<PlatformUri?>(null) }

    val imagePickerLauncher = rememberImagePicker { uri ->
        selectedImage = uri
        onUploadImage(uri)
    }

    val isAdmin =
        remember(formState?.clubMember?.user?.role) { formState?.clubMember?.user?.role == "admin" }

    RegisterStepScaffold(
        onBack = onBack,
        content = {
            Text(
                text = "Completa tu perfil",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Rellena tus datos personales para continuar con el registro",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.3f))
                    .clickable { imagePickerLauncher() },
                contentAlignment = Alignment.Center
            ) {
                when {
                    selectedImage != null -> AsyncImage(
                        model = selectedImage?.value.orEmpty(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    !formState?.clubMember?.user?.imageUrl.isNullOrEmpty() -> AsyncImage(
                        model = formState.clubMember.user.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    else -> Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Seleccionar imagen",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            FormItem(
                value = formState?.clubMember?.user?.name.orEmpty(),
                onValueChange = onNameChanged,
                label = "Nombre",
                itemIcon = Icons.Default.Person,
                modifier = Modifier
            )

            FormItem(
                value = formState?.clubMember?.user?.lastName.orEmpty(),
                onValueChange = onLastNameChanged,
                label = "Apellido",
                itemIcon = Icons.Default.Person,
                modifier = Modifier
            )

            FormItem(
                value = formState?.clubMember?.user?.phoneNumber.orEmpty(),
                onValueChange = onPhoneNumberChanged,
                label = "Número de teléfono",
                itemIcon = Icons.Default.Phone,
                modifier = Modifier,
                isRequired = false
            )

            FormItem(
                value = positionText,
                onValueChange = { position ->
                    positionText = position
                    onPositionChanged(position)
                },
                label = "Posición",
                itemIcon = Icons.Default.SportsSoccer,
                modifier = Modifier
            )

            FormItem(
                value = dorsalText,
                onValueChange = { number ->
                    dorsalText = number
                    number.toIntOrNull()?.let {
                        onNumberChanged(it)
                    }
                },
                label = "Número de dorsal",
                itemIcon = Icons.Default.FormatListNumbered,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿Es administrador de algún club?",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = formState?.clubMember?.user?.role == "admin",
                    onCheckedChange = { isChecked ->
                        val role = if (isChecked) "admin" else "player"
                        onRoleChanged(role)
                    }
                )
            }
        },
        bottomContent = {
            val user = formState?.clubMember?.user
            val player = formState?.clubMember?.player

            val canContinue = listOf(
                user?.name,
                user?.lastName,
                player?.position
            ).all { it?.isNotBlank() == true } && player?.number != null

            Button(
                onClick = {
                    if (isAdmin) onNavigateToClubSelector()
                    else onNavigateToInvitationCode()
                },
                enabled = canContinue,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Finalizar registro")
            }
        }
    )
}