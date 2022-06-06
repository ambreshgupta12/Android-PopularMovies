package com.example.android_popularmovies.presentation.movie.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.domain.usecase.GetMoviesUseCase
import com.example.android_popularmovies.domain.usecase.SaveMoviesUseCase
import com.example.android_popularmovies.presentation.movie.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val isNetworkAvailable: Boolean
) : ViewModel() {
    val movieData = MutableLiveData<List<Movie>>()
    private val movieState = MutableLiveData<MovieState>()

    init {
        movieState.value = MovieState.Init
        movieState.value = MovieState.Loading
        loadMovies()
    }

    public fun loadMovies() {
        if (isNetworkAvailable) {
            getMoviesUseCase.execute(
                onSuccess = { it ->
                    movieState.value = MovieState.MovieListSuccess(it.results)
                    it.results?.let {
                        movieData.value = it
                        saveMoviesUseCase.cacheMovies(it)
                    }

                },
                onError = {
                    movieState.value = MovieState.Error(it.localizedMessage!!)
                }
            )
        } else {
            movieData.value = saveMoviesUseCase.getCacheMovies();
        }

    }
}