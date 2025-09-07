package com.kikepb.squadfy.utils

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExportObjCClass
import platform.Foundation.NSURL
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(BetaInteropApi::class)
@ExportObjCClass
class ImagePickerController(
    private val onImagePicked: (PlatformUri) -> Unit
) : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {

    private val picker = UIImagePickerController().apply {
        sourceType = UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary
        allowsEditing = false
        delegate = this@ImagePickerController
    }

    fun present() {
        val rootVC = UIApplication.sharedApplication.keyWindow?.rootViewController
        rootVC?.presentViewController(picker, animated = true, completion = null)
    }

    override fun imagePickerController(
        picker: UIImagePickerController,
        didFinishPickingMediaWithInfo: Map<Any?, *>
    ) {
        val url = didFinishPickingMediaWithInfo[UIImagePickerControllerImageURL] as? NSURL
        if (url != null) {
            onImagePicked(PlatformUri(url))
        }
        picker.dismissViewControllerAnimated(true, completion = null)
    }

    override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, completion = null)
    }
}