package com.alviandf.moviecatalogue.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.model.MovieOrTvShowResponse

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies(): LiveData<MovieOrTvShowResponse> = repository.getMovies()

    fun getTvShows() : LiveData<MovieOrTvShowResponse> = repository.getTvShows()
}