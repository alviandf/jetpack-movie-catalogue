package com.alviandf.moviecatalogue.presentation.main

import org.junit.*
import org.junit.Assert.*

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
        viewModel.getMovies()
        viewModel.getTvShows()
    }

    @Test
    fun getMovieResponse() {
        val movies = viewModel.movieResponse
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }

    @Test
    fun getTvResponse() {
        val tvShows = viewModel.tvResponse
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}