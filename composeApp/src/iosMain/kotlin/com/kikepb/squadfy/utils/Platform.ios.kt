package com.kikepb.squadfy.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import okio.ByteString.Companion.toByteString
import platform.UIKit.UIDevice
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.darwin.ByteVar
import platform.posix.memcpy

actual fun getDeviceName(): String = UIDevice.currentDevice.name

actual fun readImageBytes(path: Any): ByteArray? {
    val url = path as? NSURL ?: return null
    val data = NSData.dataWithContentsOfURL(url) ?: return null
    return data.toByteString().toByteArray()
}



actual class PlatformUri(val nsUrl: NSURL) {
    actual val value: String get() = nsUrl.absoluteString ?: ""
}

@Composable
actual fun rememberImagePicker(onImagePicked: (PlatformUri) -> Unit): () -> Unit {
    val controller = remember { ImagePickerController(onImagePicked) }

    return {
        controller.present()
    }
}