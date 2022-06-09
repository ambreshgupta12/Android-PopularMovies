package com.example.android_popularmovies.data.source.remote

import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieBelongingList
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("movie/popular")
    fun popularMovies(): Single<MovieListModel>

    @GET("movie/{movie_id}")
    suspend fun movieDetails(@Path("movie_id") movieId: Int): Response<Movie>

    @GET("movie/{movie_id}/lists")
    suspend fun movieBelongings(@Path("movie_id") movieId: Int): Response<MovieBelongingList>
}