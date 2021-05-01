package com.alviandf.moviecatalogue.data


import androidx.lifecycle.LiveData
import com.alviandf.moviecatalogue.model.MovieOrTvShowResponse
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult

interface DataSource {

    fun getMovies(): LiveData<MovieOrTvShowResponse>

    fun getMovieOrTvShowsById(data: MovieOrTvShowResult): LiveData<MovieOrTvShowResult>

    fun getTvShows(): LiveData<MovieOrTvShowResponse>
}