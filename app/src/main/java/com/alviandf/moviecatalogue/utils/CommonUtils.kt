package com.alviandf.moviecatalogue.utils

import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.alviandf.moviecatalogue.model.MovieResponse
import com.google.gson.Gson
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Locale

fun emptyString() = ""

fun getMovieResponseFromAsset(context: Context, fileName: String): MovieResponse? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return Gson().fromJson(
        jsonString,
        MovieResponse::class.java
    )
}

fun getTvResponseFromAsset(context: Context, fileName: String): MovieResponse? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return Gson().fromJson(
        jsonString,
        MovieResponse::class.java
    )
}

fun Context.showToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.toViewDate(): String {
    return if (isNotEmpty()) {
        val format = SimpleDateFormat(
            if (this.length <= 11) "yyyy-MM-dd" else "yyyy-MM-dd HH:mm:ss",
            Locale.US
        )
        val tempDate = format.parse(this)
        String.format(
            "%d %s %s",
            tempDate?.date,
            SimpleDateFormat("MMM", Locale.US).format(tempDate?.time),
            SimpleDateFormat("yyyy", Locale.US).format(tempDate?.time)
        )
    } else emptyString()
}

fun Window.setFull(){
    this.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

fun Window.clearFull(){
    this.clearFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}