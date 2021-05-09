package com.alviandf.moviecatalogue.model

import android.os.Parcelable
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieOrTvShowResult(
    var type : String? = null,
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val genre_ids: List<Int>? = null,
    val id: Int? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
) : Parcelable

fun MovieEntity.toMovieOrTvShowResult() = MovieOrTvShowResult(
     type = type,
     backdrop_path = backdrop_path,
     id= id,
     original_language = original_language,
     name = name,
     overview = overview,
     popularity = popularity,
     poster_path = poster_path,
     title = title,
     video = video,
     vote_average = vote_average,
     vote_count = vote_count
)

fun TvShowEntity.toMovieOrTvShowResult() = MovieOrTvShowResult(
    type = type,
    backdrop_path = backdrop_path,
    id= id,
    original_language = original_language,
    name = name,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)
