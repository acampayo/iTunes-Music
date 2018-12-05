package com.itunes.music.features.songs

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
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

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(wrapperType)
        parcel.writeString(kind)
        parcel.writeInt(artistId)
        parcel.writeInt(collectionId)
        parcel.writeInt(trackId)
        parcel.writeString(artistName)
        parcel.writeString(collectionName)
        parcel.writeString(trackName)
        parcel.writeString(collectionCensoredName)
        parcel.writeString(trackCensoredName)
        parcel.writeString(collectionArtistName)
        parcel.writeString(previewUrl)
        parcel.writeString(releaseDate)
        parcel.writeString(primaryGenreName)
        parcel.writeString(currency)
        parcel.writeDouble(trackPrice)
        parcel.writeLong(trackTimeMillis)
        parcel.writeString(artworkUrl30)
        parcel.writeString(artworkUrl60)
        parcel.writeString(artworkUrl100)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}