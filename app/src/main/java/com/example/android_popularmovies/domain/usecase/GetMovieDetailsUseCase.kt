package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.domain.repository.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun execute(movieId: Int): Response<Movie> {
        return repository.getMovieDetails(movieId)
    }
}