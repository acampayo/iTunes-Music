package com.itunes.music

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.itunes.music.features.songs.SongsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class SongsActivityTest {

    private val position = 0

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SongsActivity::class.java)

    @Test
    fun testRecyclerView() {
        mActivityTestRule.activity.viewModel.songs.observeForever {
            // First scroll to the position that needs to be matched and click on it.
            Espresso.onView(ViewMatchers.withId(R.id.songsList))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))

            // Match the text in an item below the fold and check that it's displayed.
            val itemElementText = it!![position].artistName
            Espresso.onView(ViewMatchers.withText(itemElementText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}