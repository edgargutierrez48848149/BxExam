package com.example.bxexam.photos.data

import com.example.bxexam.photos.data.response.PhotosResponse
import com.example.bxexam.photos.data.response.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(@Query("albumId") album: Int): List<PhotosResponse>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostResponse
}