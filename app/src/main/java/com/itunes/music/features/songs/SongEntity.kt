package com.itunes.music.features.songs

data class SongEntity(
        val resultCount: Int = 0,
        val results: List<Song> = emptyList()
)