package com.alviandf.moviecatalogue.data


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alviandf.moviecatalogue.model.MovieOrTvShowResponse
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult

interface DataSource {

    fun getMovies(): LiveData<MovieOrTvShowResponse>

    fun getMovieOrTvShowsById(data: MovieOrTvShowResult): LiveData<MovieOrTvShowResult>

    fun getTvShows(): LiveData<MovieOrTvShowResponse>

    // Local

    fun insertMovie(movieEntity: MovieEntity)

    fun insertTvShow(tvShowEntity: TvShowEntity)

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getMovieDetail(id: Int): LiveData<MovieEntity>

    fun getTvShowDetail(id: Int): LiveData<TvShowEntity>

    fun deleteMovieById(id: Int)

    fun deleteTvShowById(id: Int)


}