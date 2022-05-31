package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.domain.model.MovieResponse
import com.example.android_popularmovies.domain.model.repository.MovieRepository
import com.example.android_popularmovies.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : SingleUseCase<MovieResponse>() {
    override fun buildUseCaseSingle(): Single<MovieResponse> {
        return repository.loadMovies()
    }
}
