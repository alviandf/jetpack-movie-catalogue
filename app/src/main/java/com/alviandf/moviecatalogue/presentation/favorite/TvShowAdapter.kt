package com.alviandf.moviecatalogue.presentation.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alviandf.moviecatalogue.model.toMovieOrTvShowResult
import com.alviandf.moviecatalogue.presentation.detail.DetailActivity
import com.alviandf.moviecatalogue.presentation.favorite.TvShowAdapter.ListViewHolder
import com.alviandf.moviecatalogue.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_data.view.imgPoster
import kotlinx.android.synthetic.main.item_row_data.view.tvName

class TvShowAdapter :
    PagedListAdapter<TvShowEntity, ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: TvShowEntity) {
            with(itemView) {
                Glide.with(this).load(Constants.URL_POSTER + data.poster_path).into(imgPoster)
                tvName.text = data.title ?: data.name
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(Constants.EXTRA_MOVIE_OR_TVSHOW, data.toMovieOrTvShowResult())
                    context.startActivity(intent)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_data, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

}