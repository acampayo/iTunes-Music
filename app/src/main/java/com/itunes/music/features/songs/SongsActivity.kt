package com.itunes.music.features.songs

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.itunes.music.R
import com.itunes.music.core.exception.Failure
import com.itunes.music.core.platform.BaseActivity

class SongsActivity : BaseActivity() {

    lateinit var viewModel: SongsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        initializeViewModel()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SongsViewModel::class.java]
        viewModel.songs.observe(this, Observer(::showSongs))
        viewModel.failure.observe(this, Observer(::handleFailure))
    }

    private fun showSongs(songs: List<Song>?) {
        Log.d("SongsRes", songs.toString())
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showFailure(getString(R.string.network_connection))
            is Failure.ServerError -> showFailure(getString(R.string.server_error))
        }
    }

    private fun showFailure(message: String) {
        //TODO: show errors in a snack bar
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun search() {
        val term = "Michael Jackson"
        viewModel.search(term)
    }
}
