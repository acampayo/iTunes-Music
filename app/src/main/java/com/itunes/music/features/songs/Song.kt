package com.itunes.music.features.songs

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.min

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
        val releaseDate: String = "",
        val primaryGenreName: String = "",
        val currency: String = "",
        val trackPrice: Double = 0.0,
        val trackTimeMillis: Long = 0,
        val artworkUrl30: String = "",
        val artworkUrl60: String = "",
        val artworkUrl100: String = ""
) {
    val formattedReleaseDate: String get() {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date = format.parse(releaseDate)

        val formatStr = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return formatStr.format(date)
    }

    val formattedLength: String get() {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(trackTimeMillis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(trackTimeMillis) % 60
        return "%02d:%02d".format(minutes, seconds)
    }

    val formattedPrice: String get() = "%.2f %s".format(trackPrice, currency)
}