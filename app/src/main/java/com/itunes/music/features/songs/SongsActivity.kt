package com.itunes.music.features.songs

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.itunes.music.R
import com.itunes.music.core.exception.Failure
import com.itunes.music.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_songs.*
import javax.inject.Inject


class SongsActivity : BaseActivity() {

    @Inject lateinit var songsAdapter: SongsAdapter

    lateinit var viewModel: SongsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs)
        appComponent.inject(this)
        initializeViewModel()
        songsList.adapter = songsAdapter
        search()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SongsViewModel::class.java]
        viewModel.songs.observe(this, Observer(::showSongs))
        viewModel.failure.observe(this, Observer(::handleFailure))
    }

    private fun showSongs(songs: List<Song>?) {
        songsList.visibility = View.VISIBLE
        emptyView.visibility = View.GONE
        songsAdapter.songs = songs.orEmpty()
        progress.visibility = View.GONE
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showFailure(getString(R.string.network_connection))
            is Failure.ServerError -> showFailure(getString(R.string.server_error))
        }
    }

    private fun showFailure(message: String) {
        songsList.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
        progress.visibility = View.GONE
        notify(message, R.string.action_reload, ::search)
    }

    private fun search() {
        songsList.visibility = View.GONE
        emptyView.visibility = View.GONE
        progress.visibility = View.VISIBLE

        val term = "Michael Jackson"
        viewModel.search(term)
    }
}
