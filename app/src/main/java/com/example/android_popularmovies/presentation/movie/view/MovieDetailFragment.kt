package com.example.android_popularmovies.presentation.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.android_popularmovies.R
import com.example.android_popularmovies.databinding.MovieDetailFragmentBinding
import com.example.android_popularmovies.presentation.movie.view_model.MovieDetailViewModel
import com.example.android_popularmovies.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding


    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<MovieDetailFragmentBinding>(
            inflater, R.layout.movie_detail_fragment, container, false
        )
        setUpViewModel()
        return binding.root
    }

    private fun setUpViewModel() {

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        val args: MovieDetailFragmentArgs by navArgs()

        viewModel.getMovieDetails(args.movieId)
        viewModel.movieDetails.observe(
            viewLifecycleOwner,
            Observer {
                binding.movie = it
                activity?.let { it1 ->
                    Glide.with(it1).load("${Constants.movieImagePath}${it.posterPath}")
                        .into(binding.moviePhoto)
                }
            }
        )
    }


}