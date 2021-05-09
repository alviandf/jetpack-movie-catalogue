package com.alviandf.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieAndTvShowDao {

    @Query("SELECT * FROM tb_favorite_movie")
    fun getListMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_favorite_tvshow")
    fun getListTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovie(movies: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShow(tvShows: TvShowEntity)

    @Query("SELECT * FROM tb_favorite_movie WHERE id = :id")
    fun getDetailMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tb_favorite_tvshow WHERE id = :id")
    fun getDetailTvShowById(id: Int): LiveData<TvShowEntity>

    @Query("delete from tb_favorite_movie where id = :id")
    fun deleteMovieById(id: Int)

    @Query("delete from tb_favorite_tvshow where id = :id")
    fun deleteTvShowById(id: Int)

}