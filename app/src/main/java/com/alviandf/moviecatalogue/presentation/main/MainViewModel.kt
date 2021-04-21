package com.alviandf.moviecatalogue.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.utils.DataDummy
import retrofit2.HttpException

class MainViewModel : ViewModel() {

    var movieResponse: List<MovieOrTvShowResult> = listOf()
    var tvResponse: List<MovieOrTvShowResult> = listOf()

    fun getMovies() {
        try {
            movieResponse = DataDummy.generateMovieDataDummy()
        } catch (e: HttpException) {
            Log.d("TAG", "Exception ${e.message}")
        } catch (e: Throwable) {
            Log.d("TAG", "Ooops: Something else went wrong")
        }
    }

    fun getTvShows() {
        try {
            tvResponse = DataDummy.generateTvShowDataDummy()
        } catch (e: HttpException) {
            Log.d("TAG", "Exception ${e.message}")
        } catch (e: Throwable) {
            Log.d("TAG", "Ooops: Something else went wrong")
        }
    }
}