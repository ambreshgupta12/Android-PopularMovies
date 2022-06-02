package com.example.android_popularmovies.presentation.movie.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.domain.usecase.GetMovieDetailsUseCase
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
    val movieDetails = MutableLiveData<MovieDetailsModel>()
    val isLoading = MutableLiveData<Boolean>()

    var job: Job? = null
    fun getMovieDetails(movieId: Int) {
        isLoading.value = true
        val requestValues = GetMovieDetailsUseCase.Params(movieId);
        job = CoroutineScope(Dispatchers.Main).launch {
            val response = getMoviesUseCase.execute(requestValues)

            if (response.isSuccessful) {
                movieDetails.value = response.body()
            } else {

            }
            isLoading.value = false
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}