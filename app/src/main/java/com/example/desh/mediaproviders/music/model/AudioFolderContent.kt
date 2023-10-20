package com.example.desh.mediaproviders.music.model

class AudioFolderContent {

    var musicFiles: ArrayList<AudioContent> = ArrayList()

    @JvmField
    var folderName: String? = null
    @JvmField
    var folderPath: String? = null
    @JvmField
    var bucket_id = 0

    val numberOfSongs: Int
        get() = musicFiles.size
}