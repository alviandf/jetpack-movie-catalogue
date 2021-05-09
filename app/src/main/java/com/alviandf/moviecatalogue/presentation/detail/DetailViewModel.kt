package com.alviandf.moviecatalogue.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getMovieOrTvShowById(data: MovieOrTvShowResult): LiveData<MovieOrTvShowResult> = repository.getMovieOrTvShowsById(data)

    fun insertMovie(movieEntity: MovieEntity) = repository.insertMovie(movieEntity)

    fun insertTvShow(tvShowEntity: TvShowEntity) = repository.insertTvShow(tvShowEntity)

    fun getMovieDetail(id: Int): LiveData<MovieEntity> = repository.getMovieDetail(id)

    fun getTvShowDetail(id: Int): LiveData<TvShowEntity> = repository.getTvShowDetail(id)

    fun deleteMovieById(id: Int) = repository.deleteMovieById(id)

    fun deleteTvShowById(id: Int) = repository.deleteTvShowById(id)
}