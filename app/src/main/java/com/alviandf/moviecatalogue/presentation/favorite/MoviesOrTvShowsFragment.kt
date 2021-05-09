package com.alviandf.moviecatalogue.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.moviecatalogue.R
import com.alviandf.moviecatalogue.presentation.main.MainViewModel
import com.alviandf.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list_movie_or_tvshow.rvMoviesOrTvShows

class MoviesOrTvShowsFragment : Fragment() {

    private var index: Int? = 0

    private lateinit var viewModel: MainViewModel

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvShowAdapter: TvShowAdapter

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) =
            MoviesOrTvShowsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie_or_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        movieAdapter = MovieAdapter()
        tvShowAdapter = TvShowAdapter()

        initViewModel()
        initRecyclerView()

        if (index == 1) {
            viewModel.getFavoriteMovies()
        } else if (index == 2) {
            viewModel.getFavoriteTvShows()
        }
    }

    private fun initRecyclerView() {
        rvMoviesOrTvShows.apply {
            layoutManager = LinearLayoutManager(requireContext())

            if (index == 1) {
                adapter = movieAdapter
            } else if (index == 2) {
                adapter = tvShowAdapter
            }

            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext())).get(MainViewModel::class.java)

        if (index == 1) {
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                movieAdapter.submitList(movies)
                movieAdapter.notifyDataSetChanged()
            })
        } else if (index == 2) {
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { movies ->
                tvShowAdapter.submitList(movies)
                tvShowAdapter.notifyDataSetChanged()
            })
        }
    }
}