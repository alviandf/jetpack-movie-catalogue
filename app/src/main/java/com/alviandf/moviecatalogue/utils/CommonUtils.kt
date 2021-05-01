package com.alviandf.moviecatalogue.utils

import android.content.Context
import com.alviandf.moviecatalogue.model.MovieOrTvShowResponse
import com.google.gson.Gson
import java.io.IOException

class CommonUtils(private val context: Context) {
    fun getMovieResponseFromAsset(fileName: String): MovieOrTvShowResponse? {
        var jsonString = ""
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return Gson().fromJson(
            jsonString,
            MovieOrTvShowResponse::class.java
        )
    }

    fun getTvResponseFromAsset(fileName: String): MovieOrTvShowResponse? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return Gson().fromJson(
            jsonString,
            MovieOrTvShowResponse::class.java
        )
    }
}