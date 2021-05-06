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
import com.alviandf.moviecatalogue.presentation.main.adapter.MovieOrTvShowAdapter
import com.alviandf.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list_movie_or_tvshow.rvMoviesOrTvShows

class MoviesOrTvShowsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var movieOrTvShowAdapter: MovieOrTvShowAdapter

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

        movieOrTvShowAdapter = MovieOrTvShowAdapter(requireContext(),listOf())

        initViewModel()
        initRecyclerView()

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
            if (index == 1) {
//                rvMoviesOrTvShows.setBackgroundColor(Color.parseColor("#ffffff"))
//                viewModel.getUserFollowers()
            } else if (index == 2) {
//                rvMoviesOrTvShows.setBackgroundColor(Color.parseColor("#000000"))
//                viewModel.getUserFollowing()
            }
    }

    private fun initRecyclerView() {
        rvMoviesOrTvShows.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieOrTvShowAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext())).get(MainViewModel::class.java)
//        viewModel.userFollowerResponse.observe(this, { response ->
//            if (response.isSuccessful) {
//                val user = response.body()
//                if (user != null) {
//                    Log.d("asdf", "initViewModel: " + user)
//                    userAdapter.users = user
//                    userAdapter.notifyDataSetChanged()
//                }
//            } else {
//                Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
//            }
//        })
//        viewModel.userFollowingResponse.observe(this, { response ->
//            if (response.isSuccessful) {
//                val user = response.body()
//                if (user != null) {
//                    Log.d("asdf", "initViewModel: " + user)
//                    userAdapter.users = user
//                    userAdapter.notifyDataSetChanged()
//                }
//            } else {
//                Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
//            }
//        })
    }
}