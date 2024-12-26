package com.example.bxexam.photos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bxexam.core.MAX_ITEMS
import com.example.bxexam.core.PREFETCH_ITEMS
import com.example.bxexam.core.UiState
import com.example.bxexam.core.makeNetworkCall
import com.example.bxexam.photos.presentation.data.PhotoModel
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) {

    fun getPhotos(): Flow<PagingData<PhotoModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {
                PagingSource(api)
            }).flow
    }

    suspend fun getPhotoDetail(id: Int):UiState<String> = makeNetworkCall {
        val responce = api.getPost(id)
        responce.body.orEmpty()
    }
}