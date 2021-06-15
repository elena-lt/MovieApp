package com.core.usecases

import com.core.repository.MovieRepository
import javax.inject.Inject

class GetSimilarMoviesUsecase @Inject constructor (
    private val repository: MovieRepository
        ) {

    suspend fun load(movieId: String) = repository.getSimilarMovies(movieId)
}