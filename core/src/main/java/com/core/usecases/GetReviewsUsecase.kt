package com.core.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.core.models.Review
import com.core.models.Reviews
import com.core.other.Resource
import com.core.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewsUsecase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getReviews(movieId: Int): Flow<PagingData<Review>> = repository.getReviews(movieId)
}