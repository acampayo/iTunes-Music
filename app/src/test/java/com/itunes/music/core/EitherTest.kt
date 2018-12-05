package com.tvshows.core

import com.itunes.music.core.functional.Either
import com.itunes.music.core.functional.Either.Left
import com.itunes.music.core.functional.Either.Right
import com.tvshows.UnitTest
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class EitherTest : UnitTest() {

    @Test
    fun testRight() {
        val result = Right("Queen")

        assertThat(result, instanceOf(Either::class.java))
        assert(result.isRight)
        assertFalse(result.isLeft)

        result.either({},
                { right ->
                    assertThat(right, instanceOf(String::class.java))
                    assertEquals(right, "Queen")
                })
    }

    @Test
    fun testLeft() {
        val result = Left("Queen")

        assertThat(result, instanceOf(Either::class.java))
        assert(result.isLeft)
        assertFalse(result.isRight)

        result.either({},
                { left ->
                    assertThat(left, instanceOf(String::class.java))
                    assertEquals(left, "Queen")
                })
    }
}