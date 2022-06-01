package com.example.android_popularmovies.presentation.movie

import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieListModel

sealed class MovieState {
    object Init : MovieState()
    object Loading : MovieState()
    data class Error(var message: String) : MovieState()
    data class MovieListSuccess(var listOfMovieViews: List<Movie>?) : MovieState()
}
