package com.alviandf.moviecatalogue.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.presentation.main.adapter.MovieOrTvShowAdapter
import kotlinx.android.synthetic.main.fragment_movie.rvMovies

class MovieFragment : Fragment() {

    private lateinit var movieOrTvShowAdapter: MovieOrTvShowAdapter
    private var movies = listOf<MovieOrTvShowResult>()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieOrTvShowAdapter = MovieOrTvShowAdapter(requireContext(), movies)
        initRecyclerView()
        initViewModel()
        viewModel.getMovies()
        getMovies()
    }

    private fun getMovies() {
        Log.d("TAG", "getMovies: " + viewModel.movieResponse)
        viewModel.movieResponse.let {
            movieOrTvShowAdapter.moviesOrTvShows = it ?: listOf()
            movieOrTvShowAdapter.notifyDataSetChanged()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initRecyclerView() {
        rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieOrTvShowAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }
}