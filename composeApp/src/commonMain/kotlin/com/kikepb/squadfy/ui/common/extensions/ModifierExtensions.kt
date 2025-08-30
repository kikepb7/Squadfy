package com.kikepb.squadfy.ui.common.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp

fun Modifier.astarBorder(isAlive: Boolean): Modifier {
    val color = if (isAlive) Yellow else Color.Red

    return border(4.dp, color, CircleShape)
}