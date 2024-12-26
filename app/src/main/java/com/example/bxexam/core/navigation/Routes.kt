package com.example.bxexam.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object ListPhotoRoute

@Serializable
data class DetailPhotoRoute(
    val body:String,
    val photo:String
)