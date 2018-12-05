package com.itunes.music.features.songs

import android.arch.lifecycle.MutableLiveData
import android.media.MediaPlayer
import com.itunes.music.core.platform.BaseViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

class SongsPlayerViewModel
@Inject constructor(): BaseViewModel() {

    var songs: List<Song> = emptyList()
    var song: MutableLiveData<Song> = MutableLiveData()
    val mediaPlayer = MediaPlayer()

    private var shouldReset: Boolean = true

    internal var trackPosition: Int by Delegates.observable(0) { _, _, _ ->
        song.value = songs[trackPosition]
    }

    fun play() {
        if (shouldReset) {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(song.value?.previewUrl)
            mediaPlayer.prepare()
        }

        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
        shouldReset = false
    }

    fun next() {
        trackPosition = trackPosition + 1
        shouldReset = true
        play()
    }

    fun previous() {
        trackPosition = trackPosition - 1
        shouldReset = true
        play()
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}