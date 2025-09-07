package com.kikepb.squadfy.utils

import androidx.compose.runtime.Composable

expect fun getDeviceName(): String
expect fun readImageBytes(path: Any): ByteArray?



expect class PlatformUri {
    val value: String
}

@Composable
expect fun rememberImagePicker(onImagePicked: (PlatformUri) -> Unit): () -> Unit