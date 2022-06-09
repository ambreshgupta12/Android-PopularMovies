package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    fun getPopularMovies(): Single<MovieListModel> {
        return repository.loadMovies()
    }


    fun cacheMovies(movies: List<Movie>) {
        repository.cacheMovie(movies);
    }

    fun getCacheMovies(): List<Movie> {
        return repository.getCacheMovies();
    }
}