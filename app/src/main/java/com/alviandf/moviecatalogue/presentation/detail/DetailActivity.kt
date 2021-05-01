package com.alviandf.moviecatalogue.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.utils.Constants
import com.alviandf.moviecatalogue.viewmodel.ViewModelFactory
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

        if (data != null) {
            initViewModel()
            getMovieOrvShowsDetail(data)
        }
    }

    private fun getMovieOrvShowsDetail(data: MovieOrTvShowResult) {
        viewModel.getMovieOrTvShowById(data).observe(this, { movieOrTvShow ->
            setUI(movieOrTvShow)
        })
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DetailViewModel::class.java)
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