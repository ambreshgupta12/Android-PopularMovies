package com.example.android_popularmovies.presentation.movie.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.domain.usecase.GetMoviesUseCase
import com.example.android_popularmovies.presentation.movie.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val isNetworkAvailable: Boolean
) : ViewModel() {
    val state: LiveData<ResultState<List<Movie>>> get() = movieState
    private val movieState = MutableLiveData<ResultState<List<Movie>>>()
    var disposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()


    init {
        movieState.value = ResultState.Init()
        movieState.value = ResultState.Loading()
        loadMovies()
    }

    fun loadMovies() {
        if (isNetworkAvailable) {
            disposable = getMoviesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.results?.let {
                        movieState.value = ResultState.Success(it)
                        getMoviesUseCase.cacheMovies(it)
                    }
                }, {
                    movieState.value = ResultState.Error(it.localizedMessage!!)
                })

            disposable?.let {
                compositeDisposable.add(it)
            }

        } else {
            movieState.value = ResultState.Success(getMoviesUseCase.getCacheMovies())
        }

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}