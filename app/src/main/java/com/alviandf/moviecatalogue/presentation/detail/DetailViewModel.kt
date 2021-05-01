package com.alviandf.moviecatalogue.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getMovieOrTvShowById(data: MovieOrTvShowResult) : LiveData<MovieOrTvShowResult> = repository.getMovieOrTvShowsById(data)
}