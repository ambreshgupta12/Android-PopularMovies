package com.example.android_popularmovies.data.repository

import com.example.android_popularmovies.data.source.local.MovieDao
import com.example.android_popularmovies.data.source.remote.MovieApiService
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.domain.repository.MovieRepository
import io.reactivex.Single
import retrofit2.Response

class MovieRepositoryImpl(
    private val service: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun loadMovies(): Single<MovieListModel> {
        return service.popularMovies()
    }

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsModel> {
        return service.movieDetails(movieId);
    }

    override fun cacheMovie(movies: List<Movie>) {
        movieDao.addMovies(movies)
    }

    override fun getCacheMovie(): List<Movie> {
        return movieDao.getMovies();
    }
}