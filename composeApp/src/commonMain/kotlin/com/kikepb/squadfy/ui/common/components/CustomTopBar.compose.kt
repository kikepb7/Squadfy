package com.kikepb.squadfy.ui.common.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.kikepb.squadfy.ui.common.theme.TopBarBackgroundColor
import com.kikepb.squadfy.ui.common.theme.TopBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = TopBarBackgroundColor,
    contentColor: Color = TopBarContentColor,
    leftIcon: ImageVector? = null,
    onLeftClick: (() -> Unit)? = null,
    rightIcon: ImageVector? = null,
    onRightClick: (() -> Unit)? = null,
    centerContent: @Composable (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            if (leftIcon != null && onLeftClick != null) {
                IconButton(onClick = onLeftClick) {
                    Icon(
                        imageVector = leftIcon,
                        contentDescription = "Navegar atrás",
                        tint = contentColor
                    )
                }
            }
        },
        title = { centerContent?.invoke() },
        actions = {
            if (rightIcon != null && onRightClick != null) {
                IconButton(onClick = onRightClick) {
                    Icon(
                        imageVector = rightIcon,
                        contentDescription = "Acción derecha",
                        tint = contentColor
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor,
            navigationIconContentColor = contentColor,
            titleContentColor = contentColor,
            actionIconContentColor = contentColor
        )
    )
}