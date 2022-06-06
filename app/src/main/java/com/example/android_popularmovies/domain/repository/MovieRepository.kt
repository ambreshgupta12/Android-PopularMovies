package com.example.android_popularmovies.domain.repository

import com.example.android_popularmovies.data.source.local.model.MovieEntity
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import io.reactivex.Single
import retrofit2.Response


interface MovieRepository {
    fun loadMovies(): Single<MovieListModel>
    fun cacheMovie(movies: List<Movie>)
    fun getCacheMovies(): List<Movie>
    fun getCacheMovie(id:Int): Movie
    suspend fun getMovieDetails(movieId: Int): Response<Movie>
}
