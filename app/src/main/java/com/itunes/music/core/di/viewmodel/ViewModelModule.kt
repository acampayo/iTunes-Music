package com.tvshows.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.itunes.music.features.songs.SongsPlayerViewModel
import com.itunes.music.features.songs.SongsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SongsViewModel::class)
    internal abstract fun songsViewModel(viewModel: SongsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SongsPlayerViewModel::class)
    internal abstract fun songsPlayerViewModel(viewModel: SongsPlayerViewModel): ViewModel

    //Add more ViewModels here
}