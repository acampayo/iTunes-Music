package com.tvshows.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
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
    internal abstract fun popularTVShowsViewModel(viewModel: SongsViewModel): ViewModel

    //Add more ViewModels here
}