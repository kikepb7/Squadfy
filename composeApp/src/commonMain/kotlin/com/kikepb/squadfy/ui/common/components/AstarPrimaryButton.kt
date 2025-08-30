package com.kikepb.squadfy.ui.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kikepb.squadfy.ui.common.theme.OutlinedButtonBorderColor
import com.kikepb.squadfy.ui.common.theme.OutlinedButtonContentColor
import com.kikepb.squadfy.ui.common.theme.OutlinedButtonTextColor
import com.kikepb.squadfy.ui.common.theme.PrimaryButtonContainerColor
import com.kikepb.squadfy.ui.common.theme.PrimaryButtonContentColor
import com.kikepb.squadfy.ui.common.theme.PrimaryButtonDisabledContainerColor
import com.kikepb.squadfy.ui.common.theme.PrimaryButtonDisabledContentColor
import com.kikepb.squadfy.ui.common.theme.PrimaryButtonTextColor

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryButtonContainerColor,
            contentColor = PrimaryButtonContentColor,
            disabledContainerColor = PrimaryButtonDisabledContainerColor.copy(alpha = 0.4f),
            disabledContentColor = PrimaryButtonDisabledContentColor.copy(alpha = 0.6f)
        )
    ) {
        Text(text = text, color = PrimaryButtonTextColor)
    }
}

@Composable
fun OutlinedAppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = OutlinedButtonContentColor
        ),
        border = BorderStroke(1.dp, OutlinedButtonBorderColor)
    ) {
        Text(text = text, color = OutlinedButtonTextColor)
    }
}