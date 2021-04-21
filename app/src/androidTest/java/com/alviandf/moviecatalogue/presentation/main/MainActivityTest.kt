package com.alviandf.moviecatalogue.presentation.main

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.utils.DataDummy
import org.junit.*

class MainActivityTest {

    private val dataDummyMovie = DataDummy.generateMovieDataDummy()
    private val dataDummyTvShow = DataDummy.generateTvShowDataDummy()
    private val position = 9

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovieAndTvShow() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(scrollToPosition<ViewHolder>(dataDummyMovie.size))

        onView(withId(R.id.navTv)).perform(click())
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(scrollToPosition<ViewHolder>(dataDummyTvShow.size))

        onView(withId(R.id.navMovie)).perform(click())
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(scrollToPosition<ViewHolder>(position))
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition<ViewHolder>(position, click()))

        onView(withId(R.id.imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvVoteCount)).check(matches(isDisplayed()))

        onView(withId(R.id.tvTitle)).check(matches(withText(dataDummyMovie[position].title)))
        onView(withId(R.id.tvDescription)).check(matches(withText(dataDummyMovie[position].overview)))
        onView(withId(R.id.tvRating)).check(matches(withText(dataDummyMovie[position].vote_average.toString())))
        onView(withId(R.id.tvVoteCount)).check(matches(withText(dataDummyMovie[position].vote_count.toString() + " votes")))

        pressBack()
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.navTv)).perform(click())

        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(scrollToPosition<ViewHolder>(position))
        onView(withId(R.id.rvTvShows)).perform(actionOnItemAtPosition<ViewHolder>(position, click()))

        onView(withId(R.id.imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvVoteCount)).check(matches(isDisplayed()))

        onView(withId(R.id.tvTitle)).check(matches(withText(dataDummyTvShow[position].title)))
        onView(withId(R.id.tvDescription)).check(matches(withText(dataDummyTvShow[position].overview)))
        onView(withId(R.id.tvRating)).check(matches(withText(dataDummyTvShow[position].vote_average.toString())))
        onView(withId(R.id.tvVoteCount)).check(matches(withText(dataDummyTvShow[position].vote_count.toString() + " votes")))

        pressBack()
    }
}