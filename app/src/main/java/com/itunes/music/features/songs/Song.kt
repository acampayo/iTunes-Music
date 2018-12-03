package com.itunes.music.features.songs

data class Song(
        val wrapperType: String = "",
        val kind: String = "",
        val artistId: Int = 0,
        val collectionId: Int = 0,
        val trackId: Int = 0,
        val artistName: String = "",
        val collectionName: String = "",
        val trackName: String = "",
        val collectionCensoredName: String = "",
        val trackCensoredName: String = "",
        val collectionArtistName: String = "",
        val previewUrl: String = "",
        val artworkUrl30: String = "",
        val artworkUrl60: String = "",
        val artworkUrl100: String = ""
)