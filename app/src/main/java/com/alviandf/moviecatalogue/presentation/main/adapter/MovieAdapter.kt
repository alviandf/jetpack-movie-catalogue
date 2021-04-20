package com.alviandf.moviecatalogue.presentation.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieResult
import com.alviandf.moviecatalogue.presentation.detail.DetailActivity
import com.alviandf.moviecatalogue.utils.BundleKeys
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_data.view.imgPoster
import kotlinx.android.synthetic.main.item_row_data.view.tvName

class MovieAdapter(
    val context: Context,
    var movies: List<MovieResult>
    ) : RecyclerView.Adapter<MovieHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_data, viewGroup, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindUser(movies[position])
    }
}

class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindUser(movie: MovieResult) {
        with(itemView) {
            Glide.with(this).load(BundleKeys.URL_POSTER + movie.poster_path).into(imgPoster)
            tvName.text = movie.original_title ?: movie.original_name
            setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(BundleKeys.EXTRA_USER, movie)
                context.startActivity(intent)
            }
        }
    }
}