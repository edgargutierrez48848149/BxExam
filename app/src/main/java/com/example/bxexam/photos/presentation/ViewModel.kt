package com.example.bxexam.photos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.bxexam.core.UiState
import com.example.bxexam.photos.data.Repository
import com.example.bxexam.photos.presentation.data.PhotoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val photosData: Flow<PagingData<PhotoModel>> = repository.getPhotos()

    fun onLoginSelected(photo: PhotoModel, navigateToDetails: (String, String) -> Unit) {
        viewModelScope.launch {
            val result = repository.getPhotoDetail(photo.id)
            if (result is UiState.Success){
                navigateToDetails(result.data,photo.url)
            }
        }
    }
}