package com.example.bxexam.photos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bxexam.photos.presentation.data.PhotoModel
import java.io.IOException
import javax.inject.Inject

class PagingSource @Inject constructor(private val api: ApiService) : PagingSource<Int, PhotoModel>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getPhotos(page)

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = page + 1

            LoadResult.Page(
                data = response.map { it.toPresentation() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}