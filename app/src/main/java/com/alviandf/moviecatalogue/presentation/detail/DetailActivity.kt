package com.alviandf.moviecatalogue.presentation.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieResult
import com.alviandf.moviecatalogue.utils.BundleKeys
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.imgBackdrop
import kotlinx.android.synthetic.main.activity_detail.ratingBar
import kotlinx.android.synthetic.main.activity_detail.tvDescription
import kotlinx.android.synthetic.main.activity_detail.tvRating
import kotlinx.android.synthetic.main.activity_detail.tvTitle
import kotlinx.android.synthetic.main.activity_detail.tvVoteCount

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<MovieResult>(BundleKeys.EXTRA_USER)

        if(data != null){
            Glide.with(this).load(BundleKeys.URL_BACKDROP + data.backdrop_path).into(imgBackdrop)
            tvTitle.text = data.title ?: data.original_name
            tvDescription.text = data.overview
            tvRating.text = data.vote_average.toString()
            tvVoteCount.text = data.vote_count.toString() + " votes"
            ratingBar.rating = ((5f * (data.vote_average!! / 10f)).toFloat())
        }
    }
}