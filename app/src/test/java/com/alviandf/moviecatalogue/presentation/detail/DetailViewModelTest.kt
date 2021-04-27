package com.alviandf.moviecatalogue.presentation.detail

import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.presentation.main.MainViewModel
import com.alviandf.moviecatalogue.utils.Constants
import com.alviandf.moviecatalogue.utils.DataDummy
import org.junit.Assert.*

import org.junit.*

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val position = 0
    private val dummyMovie = DataDummy.generateMovieDataDummy()[position]
    private val dummyTvShow = DataDummy.generateTvShowDataDummy()[position]

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getMovieOrTvShowById() {
        val movieOrTvShow = viewModel.getMovieOrTvShowById(MovieOrTvShowResult(id = dummyMovie.id, type = Constants.TYPE_MOVIE))
        assertNotNull(movieOrTvShow)
        assertEquals(dummyMovie, movieOrTvShow)
    }
}