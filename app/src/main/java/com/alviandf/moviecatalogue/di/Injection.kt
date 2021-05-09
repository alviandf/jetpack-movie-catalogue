package com.alviandf.moviecatalogue.di

import android.content.Context
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.data.source.local.LocalDataSource
import com.alviandf.moviecatalogue.data.source.local.room.MovieAndTvShowDatabase
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource
import com.alviandf.moviecatalogue.utils.AppExecutors
import com.alviandf.moviecatalogue.utils.CommonUtils

object Injection {

    fun provideRepository(context: Context): Repository {
        val remoteDataSource = RemoteDataSource.getInstance(CommonUtils(context))
        val database = MovieAndTvShowDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieAndTvShowDao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}