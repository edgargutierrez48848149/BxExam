package com.example.bxexam.photos.data.response

import com.example.bxexam.photos.presentation.data.PhotoModel
import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("albumId") val albumId:Int?,
    @SerializedName("id") val id:Int?,
    @SerializedName("title") val title:String?,
    @SerializedName("url") val url:String?,
    @SerializedName("thumbnailUrl") val thumbnailUrl:String?,
){
    fun toPresentation(): PhotoModel {
        return PhotoModel(
            id = id ?: 0,
            title = title ?: "",
            url = url ?: ""
        )
    }
}
