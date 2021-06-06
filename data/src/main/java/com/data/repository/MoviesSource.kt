package com.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.core.models.Movie
import com.core.models.Movies
import com.core.models.Review
import com.core.models.Reviews
import com.core.other.Resource
import com.data.models.ReviewApiResponse
import kotlinx.coroutines.flow.Flow

interface MoviesSource {

    suspend fun getUpcomingMovies(): Flow<PagingData<Movie>>

    suspend fun getTopRatedMovies(): Flow<PagingData<Movie>>

    suspend fun getReviews(movieId: Int): Flow<PagingData<Review>>

    suspend fun getSimilarMovies(movieId: String):  Flow<PagingData<Movie>>
}