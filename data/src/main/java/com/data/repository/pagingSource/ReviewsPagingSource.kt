package com.data.repository.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.core.models.Review
import com.data.BuildConfig
import com.data.mappers.ReviewsResponseMapper
import com.data.network.TmdbApiService
import com.data.other.Const.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException


class ReviewsPagingSource(
    private val movieApi: TmdbApiService,
    private val movieId: String
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = movieApi.getReviews(movieId, BuildConfig.TMDB_API_KEY, "en-US", position)
            val reviews = ReviewsResponseMapper.toReviews(response).results
            LoadResult.Page(
                data = reviews ?: emptyList(),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (reviews!!.size < position) null else position + 1
            )
        } catch (e: IOException) {
            Log.d(TAG, e.message ?: "Some unknown IOException")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d(TAG, e.message ?: "Some HttpException ")
            LoadResult.Error(e)
        }
    }

    companion object {
        const val TAG = "PagingSource"
    }

}