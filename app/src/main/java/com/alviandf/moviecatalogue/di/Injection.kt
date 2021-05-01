package com.alviandf.moviecatalogue.di

import android.content.Context
import com.alviandf.moviecatalogue.data.Repository
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource
import com.alviandf.moviecatalogue.utils.CommonUtils

object Injection {

    fun provideRepository(context: Context): Repository {
        val remoteDataSource = RemoteDataSource.getInstance(CommonUtils(context))
        return Repository.getInstance(remoteDataSource)
    }
}