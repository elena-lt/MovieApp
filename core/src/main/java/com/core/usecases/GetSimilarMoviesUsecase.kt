package com.core.usecases

import com.core.repository.MovieRepository

class GetSimilarMoviesUsecase (
    private val repository: MovieRepository
        ) {

    suspend fun load(movieId: String) = repository.getSimilarMovies(movieId)
}