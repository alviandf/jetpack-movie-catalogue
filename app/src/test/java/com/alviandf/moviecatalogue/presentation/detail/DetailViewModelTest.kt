package com.alviandf.moviecatalogue.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.data.source.local.entity.toMovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.toTvShowEntity
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
    private val dummyMovieEntity = dummyMovie.toMovieEntity()
    private val dummyTvShowEntity = dummyTvShow.toTvShowEntity()

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

    @Test
    fun insertMovie() {
        doNothing().`when`(repository).insertMovie(dummyMovieEntity)
        repository.insertMovie(dummyMovieEntity)

        verify(repository, times(1)).insertMovie(dummyMovieEntity)
    }

    @Test
    fun insertTvShow() {
        doNothing().`when`(repository).insertTvShow(dummyTvShowEntity)
        repository.insertTvShow(dummyTvShowEntity)

        verify(repository, times(1)).insertTvShow(dummyTvShowEntity)
    }

    @Test
    fun deleteMovieById() {
        val id = dummyMovie.id ?: 0

        doNothing().`when`(repository).deleteMovieById(id)
        repository.deleteMovieById(id)

        verify(repository, times(1)).deleteMovieById(id)
    }

    @Test
    fun deleteTvShowById() {
        val id = dummyMovie.id ?: 0

        doNothing().`when`(repository).deleteTvShowById(id)
        repository.deleteTvShowById(id)

        verify(repository, times(1)).deleteTvShowById(id)
    }
}