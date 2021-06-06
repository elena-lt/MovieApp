package com.core.usecases

import com.core.repository.MovieRepository
import javax.inject.Inject

class GetUpcomingMoviesUsecase @Inject constructor (private val movieRepository: MovieRepository) {

    suspend fun getUpcomingMovies () = movieRepository.getUpcomingMovies()
}