package com.itunes.music.core.di

import com.itunes.music.AndroidApplication
import com.itunes.music.features.songs.SongsActivity
import com.tvshows.core.di.ApplicationModule
import com.tvshows.core.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(songsActivity: SongsActivity)
}