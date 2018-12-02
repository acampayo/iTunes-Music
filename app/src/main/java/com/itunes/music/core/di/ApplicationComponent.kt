package com.itunes.music.core.di

import com.itunes.music.AndroidApplication
import com.tvshows.core.di.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
}