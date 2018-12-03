package com.itunes.music.features.songs

import com.itunes.music.core.exception.Failure
import com.itunes.music.core.functional.Either
import javax.inject.Inject

class SearchSongs
@Inject constructor(private val repository: SongsRepository) {

    operator fun invoke(term: String, onResult: (Either<Failure, List<Song>>) -> Unit = {}) {
        repository.search(term, onResult)
    }
}