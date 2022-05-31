package com.example.android_popularmovies.domain.model.repository

import com.example.android_popularmovies.domain.model.MovieResponse
import io.reactivex.Single


interface MovieRepository {
    fun loadMovies(): Single<MovieResponse>
}
