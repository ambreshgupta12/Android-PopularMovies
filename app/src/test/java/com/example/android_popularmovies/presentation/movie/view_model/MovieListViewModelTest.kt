package com.example.android_popularmovies.presentation.movie.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.domain.repository.MovieRepository
import com.example.android_popularmovies.domain.usecase.GetMoviesUseCase
import com.example.android_popularmovies.presentation.movie.state.ResultState
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MovieListViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var moviesObserver: Observer<ResultState<List<Movie>>>


    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var movieListViewModel: MovieListViewModel

    private var isNetworkAvailable: Boolean = false


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        setUpUseCases()
        setUpViewModel()
    }


    private fun setUpUseCases() {
        getMoviesUseCase = GetMoviesUseCase(movieRepository)
    }

    private fun setUpViewModel() {
        movieListViewModel = MovieListViewModel(
            getMoviesUseCase,
            isNetworkAvailable,
        )

        movieListViewModel.state.observeForever(moviesObserver)
    }

    @Test
    fun fetchMoviesList_returnsEmpty() {
        val movies = MovieListModel()
        movies.results = listOf();
        // Arrange
        stubFetchMovies(Single.just(movies))

        movieListViewModel.loadMovies()


        verify(moviesObserver, times(2)).onChanged(ResultState.Success(listOf()))
    }


    @Test
    fun fetchMoviesList_returnsData() {

        val listOfMovies = MockMovies.generateListOfMovies(10)

        val movies = MovieListModel()
        movies.results = listOfMovies;

        stubFetchMovies(Single.just(movies))

        movieListViewModel.loadMovies()
        moviesObserver.onChanged(ResultState.Success(listOfMovies))
        verify(moviesObserver).onChanged(ResultState.Success(listOfMovies))
    }


    private fun stubFetchMovies(single: Single<MovieListModel>) {
        `when`(getMoviesUseCase.execute())
            .thenReturn(single)
    }
}
