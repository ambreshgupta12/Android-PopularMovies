package com.example.android_popularmovies.presentation.movie.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.domain.usecase.GetMovieDetailsUseCase
import com.example.android_popularmovies.presentation.movie.state.ResultState
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
    val state: LiveData<ResultState<MovieDetailsModel>> get() = movieDetailsState
    private val movieDetailsState = MutableLiveData<ResultState<MovieDetailsModel>>()

    init {
        movieDetailsState.value = ResultState.Init()
    }

    var job: Job? = null
    fun getMovieDetails(movieId: Int) {
        movieDetailsState.value = ResultState.Loading()
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getMoviesUseCase.execute(movieId)
            if (response.isSuccessful) {
                CoroutineScope(Dispatchers.Main).launch {
                    movieDetailsState.value = response.body()
                        ?.let { ResultState.Success(it) }
                }
            }
        }
        job?.invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {
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