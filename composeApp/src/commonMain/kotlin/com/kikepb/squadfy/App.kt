package com.kikepb.squadfy

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.kikepb.squadfy.ui.common.navigation.NavigationWrapper

@Composable
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}