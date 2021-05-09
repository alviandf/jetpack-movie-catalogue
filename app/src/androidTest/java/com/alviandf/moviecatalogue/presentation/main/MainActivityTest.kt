package com.alviandf.moviecatalogue.presentation.main

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.R.string
import com.alviandf.moviecatalogue.utils.EspressoIdlingResource
import org.junit.*

class MainActivityTest {

    private val position = 9

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovieAndTvShow() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(scrollToPosition<ViewHolder>(position))

        onView(withId(R.id.navTv)).perform(click())
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(scrollToPosition<ViewHolder>(position))

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

        pressBack()
    }

    @Test
    fun loadFavoriteMovieAndTvShow() {
        onView(withId(R.id.rvMovies)).perform(scrollToPosition<ViewHolder>(position))
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition<ViewHolder>(position, click()))
        onView(withId(R.id.action_favorite_detail)).perform(click())

        pressBack()

        onView(withId(R.id.navTv)).perform(click())
        onView(withId(R.id.rvTvShows)).perform(scrollToPosition<ViewHolder>(position))
        onView(withId(R.id.rvTvShows)).perform(actionOnItemAtPosition<ViewHolder>(position, click()))
        onView(withId(R.id.action_favorite_detail)).perform(click())

        pressBack()

        onView(withId(R.id.navFavorite)).perform(click())
        onView(withId(R.id.rvMoviesOrTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMoviesOrTvShows)).perform(scrollToPosition<ViewHolder>(0))
        onView(withId(R.id.rvMoviesOrTvShows)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite_detail)).perform(click())

        pressBack()

        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rvMoviesOrTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMoviesOrTvShows)).perform(scrollToPosition<ViewHolder>(0))
        onView(withId(R.id.rvMoviesOrTvShows)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite_detail)).perform(click())

        pressBack()
    }
}