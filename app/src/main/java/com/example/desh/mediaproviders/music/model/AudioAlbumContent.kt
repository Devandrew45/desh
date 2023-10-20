package com.example.desh.mediaproviders.music.model

import android.net.Uri


class AudioAlbumContent {

    var albumName: String? = null
    var albumId: Long = 0
    var numberOfSongs = 0
    var albumArtUri: Uri? = null
    var albumArtist: String? = null

    private var audioContents: ArrayList<AudioContent> = ArrayList<AudioContent>()

    constructor()
    constructor(albumName: String?, albumId: Long, albumArtUri: Uri?, albumArtist: String?) {
        this.albumName = albumName
        this.albumId = albumId
        this.albumArtUri = albumArtUri
        this.albumArtist = albumArtist
    }

    fun getAudioContents(): ArrayList<AudioContent> {
        return audioContents
    }

    fun setAudioContents(audioContents: ArrayList<AudioContent>) {
        this.audioContents = audioContents
    }

    fun addAudioContent(audioContent: AudioContent) {
        audioContents.add(audioContent)
    }

    fun addNumberOfSongs() {
        numberOfSongs++
    }
}