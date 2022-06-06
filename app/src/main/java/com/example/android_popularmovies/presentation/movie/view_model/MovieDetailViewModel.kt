package com.example.android_popularmovies.presentation.movie.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.domain.usecase.GetMovieDetailsUseCase
import com.example.android_popularmovies.domain.usecase.SaveMoviesUseCase
import com.example.android_popularmovies.presentation.movie.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieDetailsUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val isNetworkAvailable: Boolean
) : ViewModel() {
    val state: LiveData<ResultState<Movie>> get() = movieDetailsState
    private val movieDetailsState = MutableLiveData<ResultState<Movie>>()

    init {
        movieDetailsState.value = ResultState.Init()
    }

    var job: Job? = null
    fun getMovieDetails(movieId: Int) {
        movieDetailsState.value = ResultState.Loading()
        job = viewModelScope.launch {
            if (isNetworkAvailable) {
                val response = getMoviesUseCase.execute(movieId)
                if (response.isSuccessful) {
                    launch {
                        movieDetailsState.value = response.body()
                            ?.let { ResultState.Success(it) }
                    }
                }
            } else {
                movieDetailsState.value = ResultState.Success(saveMoviesUseCase.getCacheMovie(movieId))
            }
        }
        job?.invokeOnCompletion {
            viewModelScope.launch {
                movieDetailsState.value =
                    it?.toString()?.let { it1 -> ResultState.Error(it1) }
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}