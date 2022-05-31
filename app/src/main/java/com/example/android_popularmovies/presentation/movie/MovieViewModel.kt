package com.example.android_popularmovies.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_popularmovies.domain.model.Movie
import com.example.android_popularmovies.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 0

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    val movieData = MutableLiveData<List<Movie>>()

    init {
        loadMovies()
    }


    private fun loadMovies() {
        getMoviesUseCase.execute(
            onSuccess = {
                if (it.results != null) {
                    movieData.value = it.results
                }
            },
            onError = {
            }
        )

    }


}