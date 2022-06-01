package com.example.android_popularmovies.presentation.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_popularmovies.R
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.databinding.MovieListFragmentBinding
import com.example.android_popularmovies.presentation.movie.adaptor.MoviesAdapter
import com.example.android_popularmovies.presentation.movie.view_model.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var binding: MovieListFragmentBinding

    companion object {
        fun newInstance() = MovieListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.movie_list_fragment, container, false
        )
        setUpViewModel()
        return binding.root
    }

    private fun setUpViewModel() {


        viewModel.movieData.observe(
            viewLifecycleOwner,
            Observer {
                setRecyclerView(it)

            }
        )
    }

    private fun setRecyclerView(list: List<Movie>) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = MoviesAdapter(list)
        }
    }

}