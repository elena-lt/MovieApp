package com.core.usecases

import com.core.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor (private val repository: MovieRepository){

    suspend fun getTopRatedMovies() = repository.getTopRatedMovies()
}