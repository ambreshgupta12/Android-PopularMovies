package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.domain.repository.MovieRepository
import com.example.android_popularmovies.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) : SingleUseCase<GetMovieDetailsUseCase.Params, MovieDetailsModel>() {
    override fun buildUseCaseSingle(requestValues: Params?): Single<MovieDetailsModel> {
        return repository.getMovieDetails(requestValues!!.movieId)
    }

    data class Params(
        val movieId: Int
    )
}