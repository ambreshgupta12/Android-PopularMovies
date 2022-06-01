package com.example.android_popularmovies.data.source.remote

import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("movie/popular")
    fun popularMovies(): Single<MovieListModel>

    @GET("movie/{movie_id}")
    fun movieDetails(@Path("movie_id") movieId: Int): Single<MovieDetailsModel>
}