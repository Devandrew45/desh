package com.example.desh.mediaproviders.utils

import android.provider.MediaStore

object Constance {

    val externalContentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    val internalContentUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI

    const val externalVolume = "external"
    const val internalVolume = "internal"


}