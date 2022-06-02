package com.example.android_popularmovies.presentation.movie.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.android_popularmovies.R
import com.example.android_popularmovies.databinding.MovieDetailFragmentBinding
import com.example.android_popularmovies.presentation.movie.state.MovieDetailState
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
        val args: MovieDetailFragmentArgs by navArgs()
        viewModel.getMovieDetails(args.movieId)
        viewModel.movieDetailsState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility =
                if (it == MovieDetailState.Loading) View.VISIBLE else View.GONE
            when (it) {
                is MovieDetailState.MovieListSuccess -> {
                    binding.movie = it.movieDetail
                    activity?.let { it1 ->
                        Glide.with(it1)
                            .load("${Constants.movieImagePath}${it.movieDetail.posterPath}")
                            .into(binding.moviePhoto)
                    }
                }
                is MovieDetailState.Error -> {
                    Log.e(TAG, "setUpViewModel: " +
                            ""+it.message, )
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}