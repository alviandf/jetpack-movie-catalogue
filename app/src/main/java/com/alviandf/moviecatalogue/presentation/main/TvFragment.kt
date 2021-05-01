package com.alviandf.moviecatalogue.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.presentation.main.adapter.MovieOrTvShowAdapter
import com.alviandf.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv.rvTvShows

class TvFragment : Fragment() {

    private lateinit var movieOrTvShowAdapter: MovieOrTvShowAdapter
    private var movies = listOf<MovieOrTvShowResult>()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieOrTvShowAdapter = MovieOrTvShowAdapter(requireContext(), movies)
        initRecyclerView()
        initViewModel()
        getTvShows()
    }

    private fun getTvShows() {
        viewModel.getTvShows().observe(viewLifecycleOwner, { movies ->
            if(movies.results != null){
                movieOrTvShowAdapter.setData(movies.results)
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext())).get(MainViewModel::class.java)
    }

    private fun initRecyclerView() {
        rvTvShows.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieOrTvShowAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }
}