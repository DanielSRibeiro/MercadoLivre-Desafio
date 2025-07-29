package com.example.mercadolivre.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.remote.SearchRemoteDataSource
import com.example.core.domain.model.ProductResults
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val query: String,
    private val dataSource: SearchRemoteDataSource
) : PagingSource<Int, ProductResults>() {

    override fun getRefreshKey(state: PagingState<Int, ProductResults>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResults> {
        return try {
            val offset = params.key ?: 0
            val limit = 10
            val response = dataSource.searchProduct(q = query, offset = offset, limit = limit)

            val nextKey = if (response.results.size < limit) null else offset + limit

            LoadResult.Page(
                data = response.results,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}