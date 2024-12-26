package com.example.bxexam.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bxexam.photos.presentation.DetailPhotoScreen
import com.example.bxexam.photos.presentation.ListPhotoScreen

@Composable
fun NavigationWrapper(paddingValues: PaddingValues){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListPhotoRoute){
        composable<ListPhotoRoute> {
            ListPhotoScreen(paddingValues){ body,photo->
                navController.navigate(DetailPhotoRoute(body,photo))
            }
        }

        composable<DetailPhotoRoute> { backStackEntry->
            val argData:DetailPhotoRoute = backStackEntry.toRoute()
            DetailPhotoScreen(paddingValues,argData.body,argData.photo)
        }
    }
}