package com.alviandf.moviecatalogue.presentation.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.presentation.detail.DetailActivity
import com.alviandf.moviecatalogue.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_data.view.imgPoster
import kotlinx.android.synthetic.main.item_row_data.view.tvName

class MovieOrTvShowAdapter(
    val context: Context,
    var moviesOrTvShows: List<MovieOrTvShowResult>
    ) : RecyclerView.Adapter<MovieHolder>() {

    fun setData(data: List<MovieOrTvShowResult>){
        moviesOrTvShows = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_data, viewGroup, false))
    }

    override fun getItemCount(): Int = moviesOrTvShows.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindUser(moviesOrTvShows[position])
    }
}

class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindUser(movieOrTvShow: MovieOrTvShowResult) {
        with(itemView) {
            Glide.with(this).load(Constants.URL_POSTER + movieOrTvShow.poster_path).into(imgPoster)
            tvName.text = movieOrTvShow.title ?: movieOrTvShow.name
            setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(Constants.EXTRA_MOVIE_OR_TVSHOW, movieOrTvShow)
                context.startActivity(intent)
            }
        }
    }
}