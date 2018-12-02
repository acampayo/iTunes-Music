package com.itunes.music

import android.app.Application
import com.itunes.music.core.di.ApplicationComponent
import com.itunes.music.core.di.DaggerApplicationComponent
import com.tvshows.core.di.ApplicationModule

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}