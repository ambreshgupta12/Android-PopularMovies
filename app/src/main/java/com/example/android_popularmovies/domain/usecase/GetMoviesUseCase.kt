package com.example.android_popularmovies.domain.usecase

import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.domain.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    var disposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()

    fun execute(
        onSuccess: ((t: MovieListModel) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        disposable = repository.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        disposable?.let {
            compositeDisposable.add(it)
        }
    }


    private fun disposeLast() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}