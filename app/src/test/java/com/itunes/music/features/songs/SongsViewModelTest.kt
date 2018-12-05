package com.itunes.music.features.songs

import com.tvshows.AndroidTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import com.itunes.music.core.functional.Either
import com.itunes.music.core.functional.Either.Left
import com.itunes.music.core.functional.Either.Right
import kotlin.test.assertEquals

class SongsViewModelTest: AndroidTest() {

    private lateinit var viewModel: SongsViewModel

    @Mock
    private lateinit var searchSongs: SearchSongs

    @Before
    fun setUp() {
        viewModel = SongsViewModel(searchSongs)
    }

    @Test
    fun search() {
        val songs = listOf(
                Song(
                        "track",
                        "song",
                        artistName = "Queen",
                        collectionName = "The Platinum Collection",
                        trackName = "Bohemian Rhapsody"
                ),
                Song(
                        "track",
                        "song",
                        artistName = "Queen",
                        collectionName = "The Platinum Collection",
                        trackName = "Another One Bites the Dust"
                )
        )

        Mockito.`when`(searchSongs("Queen")).thenAnswer { Right(songs) }

        viewModel.songs.observeForever {
            assertEquals(it!!.size, 2)

            assertEquals(it[0].wrapperType, "track")
            assertEquals(it[0].kind, "song")
            assertEquals(it[0].artistName, "Queen")
            assertEquals(it[0].collectionName, "The Platinum Collection")
            assertEquals(it[0].trackName, "Bohemian Rhapsody")

            assertEquals(it[0].wrapperType, "track")
            assertEquals(it[0].kind, "song")
            assertEquals(it[0].artistName, "Queen")
            assertEquals(it[0].collectionName, "The Platinum Collection")
            assertEquals(it[0].trackName, "Another One Bites the Dust")
        }

        viewModel.search("Queen")
    }
}