package com.example.android_popularmovies.presentation.movie.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_popularmovies.domain.usecase.GetMovieDetailsUseCase
import com.example.android_popularmovies.presentation.movie.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    val movieDetailsState = MutableLiveData<MovieDetailState>()

    init {
        movieDetailsState.value = MovieDetailState.Init
    }

    var job: Job? = null
    fun getMovieDetails(movieId: Int) {
        movieDetailsState.value = MovieDetailState.Loading
        val requestValues = GetMovieDetailsUseCase.Params(movieId);
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getMoviesUseCase.execute(requestValues)
            if (response.isSuccessful) {
                CoroutineScope(Dispatchers.Main).launch {
                    movieDetailsState.value = response.body()
                        ?.let { MovieDetailState.MovieListSuccess(it) }
                }
            }
        }
        job?.invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {
                movieDetailsState.value =
                    it?.toString()?.let { it1 -> MovieDetailState.Error(it1) }
            }

        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}