package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.MovieEntityToMovieMapper
import com.example.android_popularmovies.data.MovieToMovieEntityMapper
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.domain.repository.MovieRepository
import javax.inject.Inject

class SaveMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    fun cacheMovies(movies: List<Movie>) {
        repository.cacheMovie(movies);
    }

    fun getCacheMovies(): List<Movie> {
        return repository.getCacheMovies();
    }

    fun getCacheMovie(id:Int): Movie {
        return repository.getCacheMovie(id)
    }
}