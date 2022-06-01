package com.example.android_popularmovies.presentation.movie.state

import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel

sealed class MovieDetailState {
    object Init : MovieDetailState()
    object Loading : MovieDetailState()
    data class Error(var message: String) : MovieDetailState()
    data class MovieListSuccess(var movieDetail: MovieDetailsModel) : MovieDetailState()
}
