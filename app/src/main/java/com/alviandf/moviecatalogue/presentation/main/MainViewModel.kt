package com.alviandf.moviecatalogue.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alviandf.moviecatalogue.model.MovieResponse
import com.alviandf.moviecatalogue.utils.getMovieResponseFromAsset
import com.alviandf.moviecatalogue.utils.getTvResponseFromAsset
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var movieResponse: MutableLiveData<MovieResponse> = MutableLiveData()
    var tvResponse: MutableLiveData<MovieResponse> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            try {
                movieResponse.postValue(getMovieResponseFromAsset(getApplication(), "movie_response.json"))
            } catch (e: HttpException) {
                Log.d("TAG","Exception ${e.message}")
            } catch (e: Throwable) {
                Log.d("TAG","Ooops: Something else went wrong")
            }
        }
    }

    fun getTvShows() {
        viewModelScope.launch {
            try {
                tvResponse.postValue(getTvResponseFromAsset(getApplication(), "tv_response.json"))
            } catch (e: HttpException) {
                Log.d("TAG","Exception ${e.message}")
            } catch (e: Throwable) {
                Log.d("TAG","Ooops: Something else went wrong")
            }
        }
    }
}