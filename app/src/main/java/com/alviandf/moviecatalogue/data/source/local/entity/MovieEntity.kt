package com.alviandf.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_favorite_movie")
@Parcelize
data class MovieEntity(

    @PrimaryKey
    val id: Int? = null,
    var type : String? = null,
    val backdrop_path: String? = null,
    val original_language: String? = null,
    val name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
): Parcelable

fun MovieOrTvShowResult.toMovieEntity() = MovieEntity(
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