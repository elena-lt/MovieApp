package com.core.repository

import androidx.lifecycle.LiveData

import androidx.paging.PagingData
import com.core.models.Movie
import com.core.models.Movies
import com.core.models.Review
import com.core.other.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getUpcomingMovies(): Flow<PagingData<Movie>>

    suspend fun getTopRatedMovies(): Flow<PagingData<Movie>>

    suspend fun getReviews(movieId: Int): Flow<PagingData<Review>>

    suspend fun getSimilarMovies (movieId: String): Flow<PagingData<Movie>>
}