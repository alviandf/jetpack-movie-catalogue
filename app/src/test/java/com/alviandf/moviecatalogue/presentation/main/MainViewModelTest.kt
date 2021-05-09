package com.alviandf.moviecatalogue.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alviandf.moviecatalogue.model.MovieOrTvShowResponse
import com.alviandf.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    private val dummyMovie = DataDummy.generateMovieResponseDataDummy()
    private val dummyTvShow = DataDummy.generateTvShowResponseDataDummy()


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<MovieOrTvShowResponse>

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
        viewModel.getMovies()
        viewModel.getTvShows()
    }

    @Test
    fun getMovieResponse() {
        val movieResponse = MutableLiveData<MovieOrTvShowResponse>()
        movieResponse.value = dummyMovie

        `when`(repository.getMovies()).thenReturn(movieResponse)
        val movies = viewModel.getMovies().value

        assertNotNull(movies)
        assertEquals(dummyMovie.results?.size, movies?.results?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvResponse() {
        val tvShowResponse = MutableLiveData<MovieOrTvShowResponse>()
        tvShowResponse.value = dummyTvShow

        `when`(repository.getTvShows()).thenReturn(tvShowResponse)
        val tvShows = viewModel.getTvShows().value

        assertNotNull(tvShows)
        assertEquals(dummyTvShow.results?.size, tvShows?.results?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun getFavoriteMovies() {

        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(10)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        `when`(repository.getFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavoriteMovies().value
        Mockito.verify(repository).getFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(10, movieEntity?.size)

        viewModel.getFavoriteMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)

    }

    @Test
    fun getFavoriteTvShows() {
        val dummyTvShow = tvShowPagedList
        `when`(dummyTvShow.size).thenReturn(10)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(repository.getFavoriteTvShows()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getFavoriteTvShows().value
        Mockito.verify(repository).getFavoriteTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(10, tvShowEntity?.size)

        viewModel.getFavoriteTvShows().observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(dummyTvShow)
    }
}