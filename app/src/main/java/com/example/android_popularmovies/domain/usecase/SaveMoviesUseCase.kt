package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.domain.repository.MovieRepository
import javax.inject.Inject

class SaveMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    fun saveMovieToDb(movies: List<Movie>) {
        repository.saveMovies(movies)
    }

}