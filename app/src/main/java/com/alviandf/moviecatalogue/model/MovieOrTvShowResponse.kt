package com.alviandf.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieOrTvShowResponse(
    val page: Int?,
    val results: List<MovieOrTvShowResult>?,
    val total_pages: Int?,
    val total_results: Int?
): Parcelable