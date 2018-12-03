package com.itunes.music.features.songs

import android.arch.lifecycle.MutableLiveData
import com.itunes.music.core.platform.BaseViewModel
import javax.inject.Inject

class SongsViewModel
@Inject constructor(private val searchSongs: SearchSongs): BaseViewModel() {

    var songs: MutableLiveData<List<Song>> = MutableLiveData()

    fun search(term: String) {
        searchSongs(term) {
            it.either(::handleFailure, ::handleSongs)
        }
    }

    private fun handleSongs(songs: List<Song>) {
        this.songs.value = songs
    }
}