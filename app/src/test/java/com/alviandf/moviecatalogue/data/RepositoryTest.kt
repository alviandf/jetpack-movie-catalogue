package com.alviandf.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource
import com.alviandf.moviecatalogue.utils.Constants
import com.alviandf.moviecatalogue.utils.DataDummy
import com.alviandf.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*

import org.junit.*
import org.mockito.*

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)

    private val movieResponses = DataDummy.generateMovieResponseDataDummy()
    private val tvShowResponse = DataDummy.generateTvShowResponseDataDummy()

    private val dummyData = DataDummy.generateMovieDataDummy()[0]

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movies = LiveDataTestUtil.getValue(repository.getMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movies)
        assertEquals(movieResponses.results?.size, movies.results?.size)
    }

    @Test
    fun getMovieOrTvShowsById() {
        dummyData.type = Constants.TYPE_MOVIE

        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesAndTvShowsCallback).onAllMoviesAndTvShowsReceived(movieResponses, tvShowResponse)
            null
        }.`when`(remote).getAllMoviesAndTvShows(any())
        val movieOrTvShow = LiveDataTestUtil.getValue(repository.getMovieOrTvShowsById(dummyData))
        verify(remote).getAllMoviesAndTvShows(any())

        movieOrTvShow.type = Constants.TYPE_MOVIE

        assertNotNull(movieOrTvShow)
        assertEquals(dummyData, movieOrTvShow)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowsReceived(movieResponses)
            null
        }.`when`(remote).getAllTvShows(any())
        val tvShows = LiveDataTestUtil.getValue(repository.getTvShows())
        verify(remote).getAllTvShows(any())
        assertNotNull(tvShows)
        assertEquals(movieResponses.results?.size, tvShows.results?.size)
    }
}