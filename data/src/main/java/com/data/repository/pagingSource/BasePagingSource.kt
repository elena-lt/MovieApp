package com.data.repository.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.data.other.Const
import retrofit2.HttpException
import java.io.IOException

//abstract class BasePagingSource <Item: Any> : PagingSource<Int, Item>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
//        val anchorPosition = state.anchorPosition ?: return null
//        val page = state.closestPageToPosition(anchorPosition) ?: return null
//        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
//        return try {
//            val page = params.key ?: Const.STARTING_PAGE_INDEX
//            LoadResult.Page (
//                data = loadData(params = params) ?: listOf(),
//                prevKey = if (page == Const.STARTING_PAGE_INDEX) null else page - 1,
//                nextKey = page + 1
//                    )
//
//        }catch (e: IOException) {
//            LoadResult.Error(e)
//        } catch (e: HttpException) {
//            LoadResult.Error(e)
//        }
//    }
//
//    abstract suspend fun loadData (params: LoadParams<Int>): List<Item>?
//}