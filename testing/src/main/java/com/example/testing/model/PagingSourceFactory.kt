package com.example.testing.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.domain.model.ProductResults

class PagingSourceFactory {

    fun create(products : List<ProductResults>) = object : PagingSource<Int, ProductResults>() {
        override fun getRefreshKey(state: PagingState<Int, ProductResults>): Int = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResults> {
            return LoadResult.Page(
                data = products,
                prevKey = null,
                nextKey = 20
            )
        }

    }

}