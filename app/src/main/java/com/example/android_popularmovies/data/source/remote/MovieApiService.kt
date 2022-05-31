package com.example.android_popularmovies.data.source.remote

import com.example.android_popularmovies.domain.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApiService {
    @GET("movie/popular")
    fun popularMovies(): Single<MovieResponse>
}