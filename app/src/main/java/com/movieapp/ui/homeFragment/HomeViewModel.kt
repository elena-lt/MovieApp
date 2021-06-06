package com.movieapp.ui.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.core.usecases.GetTopRatedMoviesUseCase
import com.core.usecases.GetUpcomingMoviesUsecase
import com.movieapp.mappers.MovieMapper
import com.movieapp.models.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val upcomingMoviesUsecase: GetUpcomingMoviesUsecase,
    private val topRatedMoviesUsecase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _dataLoading: MutableLiveData<Boolean> = MutableLiveData()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _upcomingMovies: MutableLiveData<Movies> = MutableLiveData()
    val upcomingMovies: LiveData<Movies> = _upcomingMovies

    private val _topRatedMovies: MutableLiveData<Movies> = MutableLiveData()
    val topRatedMovies: LiveData<Movies> = _topRatedMovies

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    suspend fun getUpcomingMovies() =
        upcomingMoviesUsecase.getUpcomingMovies().map {
            it.map { movie ->
                MovieMapper.toSingleMovie(movie)
            }
        }

    suspend fun getTopRatedMovies() = topRatedMoviesUsecase.getTopRatedMovies().map {
        it.map { movie ->
            MovieMapper.toSingleMovie(movie)
        }
    }
}