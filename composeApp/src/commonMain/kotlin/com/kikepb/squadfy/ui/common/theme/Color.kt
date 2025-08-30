package com.kikepb.squadfy.ui.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val TopBarContentColor: Color
    @Composable get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val ErrorColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFF6F61) else Color(0xFFD32F2F)

val BackgroundAppColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1B1B1B) else Color(0xFFF5F6FA)

val PasswordRulePassedColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF64D97B) else Color(0xFF2E7D32)

val PasswordRuleFailedColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFB0B0B0) else Color(0xFF9E9E9E)

val PrimarySuccessColor: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF66BB6A) else Color(0xFF4CAF50)

val SectionTitleColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFB0BEC5) else Color(0xFF37474F)

val PrimaryButtonDisabledColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1B5E20) else Color(0xFFB2DFDB)

val SurfaceColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1E1E1E) else Color(0xFFF5F5F5)

val TopBarBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1B1B1B) else Color(0xFFE0F2F1)

val PrimaryTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF212121)

val SecondaryTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFB0BEC5) else Color(0xFF757575)






val NeonGreen = Color(0xFF1dc690)
val BlueGrotto = Color(0xFF278ab0)
val Blue = Color(0xFF1c4670)
val Ivory = Color(0xFFeaeae0)



// === BACKGROUND ===
val BackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF121212) else Ivory

val CurvedBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1E1E1E) else Color(0xFFF5F5F5)



// === BUTTONS ===
val PrimaryButtonContainerColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) NeonGreen else BlueGrotto

val PrimaryButtonContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color.White

val PrimaryButtonDisabledContainerColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF444444) else Color(0xFFD3D3D3)

val PrimaryButtonDisabledContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFAAAAAA) else Color(0xFF888888)

val OutlinedButtonContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) NeonGreen else Blue

val OutlinedButtonBorderColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1dc690) else Color(0xFF1c4670)

val PrimaryButtonTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color.White

val OutlinedButtonTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1dc690) else Color.Black



// === CARDS ===
val DashboardHeaderContainerColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1C2A33) else Color(0xFF278ab0)

val DashboardHeaderClubNameColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFeaeae0) else Color.White
val MatchCardContainerColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1A1C1E) else Color(0xFFA8C1C9)

val MatchCardBorderColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1dc690).copy(alpha = 0.4f) else Color(0xFF1c4670).copy(alpha = 0.3f)

val MatchCardDateColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFB0BEC5) else Color(0xFF4F4F4F)

val MatchCardScoreColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFeaeae0) else Color(0xFF1c4670)

val PlayerCardContainerColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF23272A) else Color(0xFFE9F4F7)

val PlayerCardBorderColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1dc690).copy(alpha = 0.4f) else Color(0xFF1c4670).copy(alpha = 0.25f)

val PlayerCardNameColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFEAEAEA) else Color(0xFF1c4670)

val PlayerCardPositionColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFB0BEC5) else Color(0xFF4F4F4F)

val PlayerCardIconColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF888888) else Color(0xFF9E9E9E)