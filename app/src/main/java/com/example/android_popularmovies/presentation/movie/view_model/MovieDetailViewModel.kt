package com.example.android_popularmovies.presentation.movie.view_model

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
    val isLoading = MutableLiveData<Boolean>()


     fun getMovieDetails(movieId: Int) {
        isLoading.value = true
        getMoviesUseCase.requestValues = GetMovieDetailsUseCase.Params(movieId);
        getMoviesUseCase.execute(
            onSuccess = {
                movieDetails.value = it
                isLoading.value = false
            },
            onError = {
                isLoading.value = false
            }
        )
    }
}