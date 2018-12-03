package com.itunes.music.features.songs

import com.itunes.music.core.exception.Failure
import com.itunes.music.core.exception.Failure.ServerError
import com.itunes.music.core.exception.Failure.NetworkConnection
import com.itunes.music.core.functional.Either
import com.itunes.music.core.functional.Either.Left
import com.itunes.music.core.functional.Either.Right
import com.itunes.music.core.platform.NetworkHandler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

interface SongsRepository {

    fun search(term: String, onResult: (Either<Failure, List<Song>>) -> Unit)

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: SongsService) : SongsRepository {

        override fun search(term: String, onResult: (Either<Failure, List<Song>>) -> Unit) {
            return when (networkHandler.isConnected) {
                true -> request(service.search(term), { it.results }, SongEntity(), onResult)
                false, null -> onResult(Left(NetworkConnection()))
            }
        }

        private fun <T, R> request(call: Observable<Response<T>>, transform: (T) -> R, default: T,
                                   onResult: (Either<Failure, R>) -> Unit = {}) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        try {
                            val result = when (it.isSuccessful) {
                                true -> Right(transform(it.body() ?: default))
                                false -> Left(ServerError())
                            }
                            onResult(result)
                        } catch (exception: Throwable) {
                            exception.printStackTrace()
                            onResult(Left(ServerError()))
                        }
                    }, {
                        it.printStackTrace()
                        onResult(Left(ServerError()))
                    })
        }
    }
}