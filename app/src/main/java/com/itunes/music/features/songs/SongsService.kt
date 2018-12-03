package com.itunes.music.features.songs

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongsService
@Inject constructor(retrofit: Retrofit) : SongsApi {
    private val songsApi by lazy { retrofit.create(SongsApi::class.java) }
    override fun search(term: String) = songsApi.search(term)
}