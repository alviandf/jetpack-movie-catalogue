package com.alviandf.moviecatalogue.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.data.source.local.entity.toMovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.toTvShowEntity
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

    private lateinit var viewModel: DetailViewModel
    private lateinit var itemFavorite: MenuItem
    private lateinit var detailData: MovieOrTvShowResult
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<MovieOrTvShowResult>(Constants.EXTRA_MOVIE_OR_TVSHOW)


        if (data != null) {
            detailData = data

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
        if (data.vote_average != null) {
            ratingBar.rating = ((5f * (data.vote_average / 10f)).toFloat())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)

        itemFavorite = menu!!.findItem(R.id.action_favorite_detail)
        getMovieOrTvShowFavorite()

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite_detail) {

            if (isFavorite) {
                deleteMovieOrTvShow()
            } else {
                insertMovieOrTvShow()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteMovieOrTvShow() {
        if (detailData.type == Constants.TYPE_MOVIE) {
            viewModel.deleteMovieById(detailData.id ?: 0)
        } else if (detailData.type == Constants.TYPE_TVSHOW) {
            viewModel.deleteTvShowById(detailData.id ?: 0)
        }
        itemFavorite.setIcon(R.drawable.ic_favorite_false)
    }

    private fun insertMovieOrTvShow() {
        if (detailData.type == Constants.TYPE_MOVIE) {
            viewModel.insertMovie(detailData.toMovieEntity())
        } else if (detailData.type == Constants.TYPE_TVSHOW) {
            viewModel.insertTvShow(detailData.toTvShowEntity())
        }
        itemFavorite.setIcon(R.drawable.ic_favorite_true)
    }

    private fun getMovieOrTvShowFavorite() {

        if (detailData.type == Constants.TYPE_MOVIE) {
            detailData.id?.let {
                viewModel.getMovieDetail(it).observe(this) {
                    isFavorite = if (it != null) {
                        itemFavorite.setIcon(R.drawable.ic_favorite_true)
                        true
                    } else {
                        itemFavorite.setIcon(R.drawable.ic_favorite_false)
                        false
                    }
                }
            }
        } else if (detailData.type == Constants.TYPE_TVSHOW) {
            detailData.id?.let {
                viewModel.getTvShowDetail(it).observe(this) {
                    isFavorite = if (it != null) {
                        itemFavorite.setIcon(R.drawable.ic_favorite_true)
                        true
                    } else {
                        itemFavorite.setIcon(R.drawable.ic_favorite_false)
                        false
                    }
                }
            }
        }
    }
}