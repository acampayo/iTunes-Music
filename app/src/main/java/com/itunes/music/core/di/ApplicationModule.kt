package com.tvshows.core.di

import android.content.Context
import com.itunes.music.AndroidApplication
import com.itunes.music.features.songs.SongsApi
import com.itunes.music.features.songs.SongsRepository
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides @Singleton fun provideApplicationContext(): Context = application
    @Provides @Singleton fun songsRepositoryProvider(
            network: SongsRepository.Network): SongsRepository = network

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(SongsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.
                        createWithScheduler(Schedulers.io()))
                .build()
    }
}
