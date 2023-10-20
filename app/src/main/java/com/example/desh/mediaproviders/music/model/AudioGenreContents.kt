package com.example.desh.mediaproviders.music.model

class AudioGenreContents {

    private var audioFiles: ArrayList<AudioContent> = ArrayList()

    @JvmField
    var genreName: String? = null

    @JvmField
    var genreId: String? = null

    fun addGenreMusic(music: AudioContent) {
        audioFiles.add(music)
    }
}