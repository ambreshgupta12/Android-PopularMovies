package com.example.android_popularmovies.domain.repository

import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import io.reactivex.Single


interface MovieRepository {
    fun loadMovies(): Single<MovieListModel>
    fun cacheMovie(movies: List<Movie>)
    fun getCacheMovie( ):List<Movie>
    fun getMovieDetails(movieId:Int): Single<MovieDetailsModel>
}
