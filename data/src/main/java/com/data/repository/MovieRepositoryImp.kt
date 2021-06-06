package com.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.core.models.Movie
import com.core.models.Movies
import com.core.models.Review
import com.core.models.Reviews
import com.core.other.Resource
import com.core.repository.MovieRepository
import com.data.models.ReviewApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val moviesSource: MoviesSource
) : MovieRepository {

    override suspend fun getUpcomingMovies() = moviesSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): Flow<PagingData<Movie>> = moviesSource.getTopRatedMovies()

    override fun getReviews(movieId: Int): Flow<PagingData<Review>> = moviesSource.getReviews(movieId)
}