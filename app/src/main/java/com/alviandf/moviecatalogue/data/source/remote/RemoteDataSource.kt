package com.alviandf.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import com.alviandf.moviecatalogue.model.MovieOrTvShowResponse
import com.alviandf.moviecatalogue.utils.CommonUtils
import com.alviandf.moviecatalogue.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val jsonHelper: CommonUtils) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: CommonUtils): RemoteDataSource =
                instance ?: synchronized(this) {
                    RemoteDataSource(helper).apply { instance = this }
                }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            val movies = jsonHelper.getMovieResponseFromAsset("movie_response.json")
            if (movies != null) {
                callback.onAllMoviesReceived(movies)
            }
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            val tvShows = jsonHelper.getTvResponseFromAsset("tv_response.json")
            if (tvShows != null) {
                callback.onAllTvShowsReceived(tvShows)
            }
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllMoviesAndTvShows(callback: LoadMoviesAndTvShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            val movies = jsonHelper.getMovieResponseFromAsset("movie_response.json")
            val tvShows = jsonHelper.getTvResponseFromAsset("tv_response.json")
            if (movies != null && tvShows != null) {
                callback.onAllMoviesAndTvShowsReceived(movies, tvShows)
            }
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: MovieOrTvShowResponse)
    }

    interface LoadMoviesAndTvShowsCallback {
        fun onAllMoviesAndTvShowsReceived(movies: MovieOrTvShowResponse, tvShows: MovieOrTvShowResponse)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(movieResponses: MovieOrTvShowResponse)
    }
}

