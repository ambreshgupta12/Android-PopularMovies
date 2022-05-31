package com.example.android_popularmovies.data.repository

import com.example.android_popularmovies.data.source.remote.MovieApiService
import com.example.android_popularmovies.domain.model.MovieResponse
import com.example.android_popularmovies.domain.model.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImpl(
    private val service: MovieApiService
) : MovieRepository {

    override fun loadMovies(): Single<MovieResponse> {
        return service.popularMovies()
    }

}