package com.alviandf.moviecatalogue.presentation.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.imgBackdrop
import kotlinx.android.synthetic.main.activity_detail.ratingBar
import kotlinx.android.synthetic.main.activity_detail.tvDescription
import kotlinx.android.synthetic.main.activity_detail.tvRating
import kotlinx.android.synthetic.main.activity_detail.tvTitle
import kotlinx.android.synthetic.main.activity_detail.tvVoteCount

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<MovieOrTvShowResult>(Constants.EXTRA_MOVIE_OR_TVSHOW)

        Log.d("TAG", "onCreate: " + data)

        if (data != null) {
            initViewModel(data)
        }
    }

    private fun initViewModel(data: MovieOrTvShowResult) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val result = viewModel.getMovieOrTvShowById(data)
        Log.d("TAG", "initViewModel: " + result)
        setUI(result)
    }

    @SuppressLint("SetTextI18n")
    private fun setUI(data: MovieOrTvShowResult) {
        Glide.with(this).load(Constants.URL_BACKDROP + data.backdrop_path).into(imgBackdrop)
        tvTitle.text = data.title ?: data.name
        tvDescription.text = data.overview
        tvRating.text = data.vote_average.toString()
        tvVoteCount.text = data.vote_count.toString() + " votes"
        if(data.vote_average != null){
            ratingBar.rating = ((5f * (data.vote_average / 10f)).toFloat())
        }
    }
}