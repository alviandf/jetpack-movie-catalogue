package com.alviandf.moviecatalogue.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieResult
import com.alviandf.moviecatalogue.presentation.main.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.rvMovies

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private var movies = listOf<MovieResult>()
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

        movieAdapter = MovieAdapter(requireContext(), movies)
        initRecyclerView()
        initViewModel()
        viewModel.getMovies()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.movieResponse.observe(viewLifecycleOwner, {
            movieAdapter.movies = it.results ?: listOf()
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView() {
        rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }
}