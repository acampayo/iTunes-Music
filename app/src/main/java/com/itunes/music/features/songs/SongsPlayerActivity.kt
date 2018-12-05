package com.itunes.music.features.songs

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.itunes.music.R
import com.itunes.music.core.exception.Failure
import com.itunes.music.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_songs_player.*
import kotlinx.android.synthetic.main.content_songs_player.*

class SongsPlayerActivity : BaseActivity() {

    lateinit var viewModel: SongsPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_player)
        appComponent.inject(this)
        initializeViewModel()
    }

    fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SongsPlayerViewModel::class.java]
        viewModel.song.observe(this, Observer(::showSong))
        viewModel.failure.observe(this, Observer(::handleFailure))

        val trackPosition: Int = intent.getIntExtra(SongsConstants.SELECTED_SONG_POSITION, 0)
        val songs = intent.getParcelableArrayListExtra<Song>(SongsConstants.SONGS_KEY)

        viewModel.songs = songs
        viewModel.trackPosition = trackPosition
    }

    private fun showSong(song: Song?){
        if (song == null) return
        setUpToolbar(song)

        Glide.with(applicationContext)
                .load(song.artworkUrl100)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(cover)

        artist.text = song.artistName
        album.text = song.collectionName
        releaseDate.text = song.formattedReleaseDate
        length.text = song.formattedLength
        genre.text = song.primaryGenreName
        price.text = song.formattedPrice
    }

    private fun setUpToolbar(song: Song) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarLayout.title = song.trackName
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showFailure(getString(R.string.network_connection))
            is Failure.ServerError -> showFailure(getString(R.string.server_error))
        }
    }

    private fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onPlayPause(view: View) {
        if (viewModel.mediaPlayer.isPlaying) {
            viewModel.pause()
            playPauseButton.setImageResource(android.R.drawable.ic_media_play)
        } else {
            viewModel.play()
            playPauseButton.setImageResource(android.R.drawable.ic_media_pause)
        }
    }

    fun onPrevious(view: View) {
        viewModel.previous()
        setPlayPauseButton()
    }

    fun onNext(view: View) {
        viewModel.next()
        setPlayPauseButton()
    }

    private fun setPlayPauseButton() {
        if (viewModel.mediaPlayer.isPlaying) {
            playPauseButton.setImageResource(android.R.drawable.ic_media_pause)
        } else {
            playPauseButton.setImageResource(android.R.drawable.ic_media_play)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stop()
    }
}
