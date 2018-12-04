package com.itunes.music.features.songs

sealed class SongsSort {

    class Length: SongsSort()
    class Genre: SongsSort()
    class Price: SongsSort()
}
