package com.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.core.models.Movie
import com.core.models.Movies
import com.core.models.Review
import com.core.other.Resource
import com.data.BuildConfig
import com.data.mappers.MoviesResponseMapper
import com.data.network.TmdbApiService
import com.data.repository.pagingSource.ReviewsPagingSource
import com.data.repository.pagingSource.TopRatedPagingSource
import com.data.repository.pagingSource.UpcomingMoviesPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
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
            { UpcomingMoviesPagingSource(apiService) }
        ).flow

//    override suspend fun getUpcomingMovies(): Resource<Movies> = withContext(Dispatchers.IO) {
//        try {
//            val response = apiService.getUpcomingMovies(BuildConfig.TMDB_API_KEY)
//            if (response.isSuccessful && response.body() != null) {
//                return@withContext Resource.SUCCESS(MoviesResponseMapper.toMovies(response.body()!!))
//            } else {
//                Log.d(TAG, "Error: ${response.message()}")
//                return@withContext Resource.ERROR(response.message(), null)
//            }
//        } catch (e: Exception) {
//            Log.d(TAG, "Error: ${e.message.toString()}")
//            return@withContext Resource.ERROR(e.message, null)
//        }
//    }


    override suspend fun getTopRatedMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory =
            { TopRatedPagingSource(apiService) }
        ).flow

    /**
     * @param prefetchDistance //TODO
     * @param maxSize - number of cached elements //TODO
     */
    override fun getReviews(movieId: Int): Flow<PagingData<Review>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory =
            { ReviewsPagingSource(apiService, movieId.toString()) }
        ).flow


    companion object {
        const val TAG = "MainActivitySource"
    }

}