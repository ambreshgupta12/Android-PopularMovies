package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.domain.repository.MovieRepository
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
   suspend fun execute(requestValues: Params?): Response<MovieDetailsModel> {
        return repository.getMovieDetails(requestValues!!.movieId)
    }

    data class Params(
        val movieId: Int
    )
}