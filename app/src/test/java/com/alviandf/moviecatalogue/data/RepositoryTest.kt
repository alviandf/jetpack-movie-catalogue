package com.alviandf.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource.Factory
import com.alviandf.moviecatalogue.data.source.local.LocalDataSource
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource
import com.alviandf.moviecatalogue.utils.AppExecutors
import com.alviandf.moviecatalogue.utils.Constants
import com.alviandf.moviecatalogue.utils.DataDummy
import com.alviandf.moviecatalogue.utils.LiveDataTestUtil
import com.alviandf.moviecatalogue.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.Assert.*
import org.mockito.*
import org.mockito.Mockito.*

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val repository = FakeRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateMovieResponseDataDummy()
    private val tvShowResponse = DataDummy.generateTvShowResponseDataDummy()

    private val listMovie = DataDummy.generateMovieDataDummy()
    private val listTvShow = DataDummy.generateTvShowDataDummy()
    private val movie = listMovie[0]
    private val tvShow = listTvShow[0]

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
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesAndTvShowsCallback).onAllMoviesAndTvShowsReceived(
                movieResponses,
                tvShowResponse
            )
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

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(Factory::class.java) as Factory<Int, MovieEntity>
        `when`(local.getListMovies()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovies()

        val movieEntity = PagedListUtil.mockPagedList(listMovie)
        Mockito.verify(local).getListMovies()
        assertNotNull(movieEntity)
        assertEquals(listMovie.size, movieEntity.size)
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(Factory::class.java) as Factory<Int, TvShowEntity>
        `when`(local.getListTvShows()).thenReturn(dataSourceFactory)
        repository.getFavoriteTvShows()

        val tvShowEntity = PagedListUtil.mockPagedList(listTvShow)
        Mockito.verify(local).getListTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(listTvShow.size, tvShowEntity.size)
    }
}