package com.itunes.music.features.songs

import android.arch.lifecycle.MutableLiveData
import com.itunes.music.core.platform.BaseViewModel
import com.itunes.music.features.songs.SongsSort.*
import javax.inject.Inject

class SongsViewModel
@Inject constructor(private val searchSongs: SearchSongs): BaseViewModel() {

    var songs: MutableLiveData<MutableList<Song>> = MutableLiveData()

    fun search(term: String) {
        searchSongs(term) {
            it.either(::handleFailure, ::handleSongs)
        }
    }

    private fun handleSongs(songs: List<Song>) {
        this.songs.value = songs.toMutableList()
    }

    fun sort(songsSort: SongsSort) {
        when(songsSort) {
            is Length -> songs.value?.sortBy { it.trackTimeMillis }
            is Genre -> songs.value?.sortBy { it.primaryGenreName }
            is Price -> songs.value?.sortBy { it.trackPrice }
        }
    }
}