package com.alviandf.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Query
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alviandf.moviecatalogue.data.source.local.room.MovieAndTvShowDao

class LocalDataSource private constructor(private val movieAndTvShowDao: MovieAndTvShowDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(academyDao: MovieAndTvShowDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(academyDao).apply {
                INSTANCE = this
            }

    }

    fun getListMovies() : DataSource.Factory<Int, MovieEntity> = movieAndTvShowDao.getListMovies()

    fun getListTvShows() : DataSource.Factory<Int, TvShowEntity> = movieAndTvShowDao.getListTvShows()

    fun insertMovie(movies: MovieEntity) = movieAndTvShowDao.insertMovie(movies)

    fun insertTvShow(tvShow: TvShowEntity) = movieAndTvShowDao.insertTvShow(tvShow)

    fun getDetailMovie(id: Int) : LiveData<MovieEntity> = movieAndTvShowDao.getDetailMovieById(id)

    fun getDetailTvShow(id: Int) : LiveData<TvShowEntity> = movieAndTvShowDao.getDetailTvShowById(id)

    fun deleteMovieById(id: Int) = movieAndTvShowDao.deleteMovieById(id)

    fun deleteTvShowById(id: Int) = movieAndTvShowDao.deleteTvShowById(id)
}