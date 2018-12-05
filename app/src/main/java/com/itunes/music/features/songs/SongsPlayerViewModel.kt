package com.itunes.music.features.songs

import android.arch.lifecycle.MutableLiveData
import com.itunes.music.core.platform.BaseViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

class SongsPlayerViewModel
@Inject constructor(private val searchSongs: SearchSongs): BaseViewModel() {

    var songs: List<Song> = emptyList()
    var song: MutableLiveData<Song> = MutableLiveData()

    internal var trackPosition: Int by Delegates.observable(0) { _, _, _ ->
        song.value = songs[trackPosition]
    }
}