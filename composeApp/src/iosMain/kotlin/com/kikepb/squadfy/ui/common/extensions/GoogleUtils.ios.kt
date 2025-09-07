package com.kikepb.squadfy.ui.common.extensions

import androidx.compose.runtime.Composable

@Composable
actual fun rememberGoogleSignIn(onTokenReceived: (String) -> Unit): () -> Unit {
    return {
        println("Google Sign-In no implementado en iOS todavía")
    }
}