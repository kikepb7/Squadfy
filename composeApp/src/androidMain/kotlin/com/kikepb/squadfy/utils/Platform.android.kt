package com.kikepb.squadfy.utils

import android.os.Build
import android.net.Uri
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

actual fun getDeviceName(): String = "${Build.MANUFACTURER} ${Build.MODEL}"

actual fun readImageBytes(path: Any): ByteArray? {
    return if (path is Uri && path is Uri) {
        val context = path as? Context
        context?.contentResolver?.openInputStream(path)?.readBytes()
    } else null
}

actual class PlatformUri(val uri: Uri) {
    actual val value: String get() = uri.toString()
}

@Composable
actual fun rememberImagePicker(onImagePicked: (PlatformUri) -> Unit): () -> Unit {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onImagePicked(PlatformUri(it)) }
    }

    return { launcher.launch("image/*") }
}