package com.sample.machinetask.repository

import androidx.paging.PagingSource
import com.sample.machinetask.model.APIResponse
import com.sample.machinetask.network.WebServices

class DataRepository (
    val backend: WebServices
) : PagingSource<Int, APIResponse.Article>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, APIResponse.Article> {
        try {
            // Load page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = backend.getArticles("72c2cf7d168d421e8e60b90b4d266273",nextPageNumber,20)
            return LoadResult.Page(
                data = response.articles,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            // Handle errors in this block
            return LoadResult.Error(e)
        }
    }
}