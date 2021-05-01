package com.alviandf.moviecatalogue.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val position = 0
    private val dummyMovie = DataDummy.generateMovieDataDummy()[position]
    private val dummyTvShow = DataDummy.generateTvShowDataDummy()[position]

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<MovieOrTvShowResult>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieOrTvShowById() {
        val movieOrTvShow = MutableLiveData<MovieOrTvShowResult>()
        movieOrTvShow.value = dummyMovie

        `when`(repository.getMovieOrTvShowsById(dummyMovie)).thenReturn(movieOrTvShow)
        val movieOrTvShowData = viewModel.getMovieOrTvShowById(dummyMovie).value
        verify(repository).getMovieOrTvShowsById(dummyMovie)

        assertNotNull(movieOrTvShowData)
        assertEquals(dummyMovie, movieOrTvShowData)

        viewModel.getMovieOrTvShowById(dummyMovie).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}