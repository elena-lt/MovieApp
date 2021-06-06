package com.data.repository.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.core.models.Movie
import com.data.BuildConfig
import com.data.mappers.MoviesResponseMapper
import com.data.network.TmdbApiService
import com.data.other.Const
import retrofit2.HttpException
import java.io.IOException

class TopRatedPagingSource (
    private val movieApi: TmdbApiService
        ) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: Const.STARTING_PAGE_INDEX

        return try {
            val response = movieApi.getTopRatedMovies(BuildConfig.TMDB_API_KEY, "en-US", position)
            if (response.isSuccessful){
                val movies = response.body()?.movieList?.map { movie ->
                    MoviesResponseMapper.toSingleMovie(movie)
                }
                LoadResult.Page(
                    data = movies ?: emptyList(),
                    prevKey = if (position == Const.STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (movies!!.size < position) null else position + 1
                )
            }else {
                LoadResult.Error(HttpException(response))
            }

        } catch (e: IOException) {
            Log.d(ReviewsPagingSource.TAG, e.message ?: "Some unknown IOException")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d(ReviewsPagingSource.TAG, e.message ?: "Some HttpException ")
            LoadResult.Error(e)
        }
    }
}