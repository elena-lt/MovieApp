package com.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.core.models.Movie
import com.core.models.Review
import com.core.other.CollectionType
import com.data.network.TmdbApiService
import com.data.repository.pagingSource.MoviePagingSource
import com.data.repository.pagingSource.ReviewsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesSourceImp @Inject constructor(
    val apiService: TmdbApiService,
) : MoviesSource {


    override suspend fun getUpcomingMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory =
            {MoviePagingSource(apiService, CollectionType.UPCOMING_MOVIES)}
        ).flow

    override suspend fun getTopRatedMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory =
            {MoviePagingSource(apiService, CollectionType.TOP_RATED_MOVIES)}
        ).flow

    /**
     * @param prefetchDistance //TODO
     * @param maxSize - number of cached elements //TODO
     */
    override suspend fun getReviews(movieId: Int): Flow<PagingData<Review>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory =
            { ReviewsPagingSource(apiService, movieId.toString()) }
        ).flow

    override suspend fun getSimilarMovies(movieId: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory =
            { MoviePagingSource(apiService, CollectionType.SIMILAR_MOVIES, movieId) }
        ).flow



    companion object {
        const val TAG = "MainActivitySource"
    }

}