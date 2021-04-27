package com.alviandf.moviecatalogue.presentation.detail

import androidx.lifecycle.ViewModel
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.utils.Constants
import com.alviandf.moviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {

    var movieOrTvShowResult: MovieOrTvShowResult = MovieOrTvShowResult()

    fun getMovieOrTvShowById(data: MovieOrTvShowResult) : MovieOrTvShowResult {
        var listMovieOrTvShow = listOf<MovieOrTvShowResult>()

        if(data.type == Constants.TYPE_MOVIE){
            listMovieOrTvShow = DataDummy.generateMovieDataDummy()
        }else if(data.type == Constants.TYPE_TVSHOW){
            listMovieOrTvShow = DataDummy.generateTvShowDataDummy()
        }

        for (movieOrTvShow in listMovieOrTvShow){
            if (movieOrTvShow.id == data.id){
                movieOrTvShowResult = movieOrTvShow
                break
            }
        }

        return movieOrTvShowResult
    }
}