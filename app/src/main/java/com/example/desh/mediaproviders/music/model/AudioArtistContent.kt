package com.example.desh.mediaproviders.music.model

class AudioArtistContent {

    @JvmField
    var artistName: String? = null
    @JvmField
    var albums = ArrayList<AudioAlbumContent>()
    val numOfSongs: Int
        get() {
            var numOfSongs = 0
            for (i in albums.indices) {
                numOfSongs += albums[i].numberOfSongs
            }
            return numOfSongs
        }
}