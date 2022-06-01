package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.domain.repository.MovieRepository
import com.example.android_popularmovies.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : SingleUseCase<Void, MovieListModel>() {
    override fun buildUseCaseSingle(requestValues: Void?): Single<MovieListModel> {

        return repository.loadMovies()

    }

}