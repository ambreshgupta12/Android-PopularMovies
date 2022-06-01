package com.example.android_popularmovies.presentation.movie_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    val movieDetails = MutableLiveData<MovieDetailsModel>()

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        getMoviesUseCase.requestValues = GetMovieDetailsUseCase.Params(32423);
        getMoviesUseCase.execute(
            onSuccess = {
                movieDetails.value = it
            },
            onError = {
            }
        )
    }
}