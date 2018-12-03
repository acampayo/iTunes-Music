package com.itunes.music.features.songs

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SongsApi {

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        const val SEARCH = "search"
        const val TERM_PARAM = "term"
    }

    @GET(SEARCH)
    fun search(@Query(TERM_PARAM) term: String): Observable<Response<SongEntity>>
}