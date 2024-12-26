package com.example.bxexam.photos.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DetailPhotoScreen(
    paddingValues: PaddingValues,
    body:String,
    photo:String){
    Column(modifier = Modifier.padding(paddingValues)) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            model = photo,
            contentDescription = "image"
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = body,
        )
    }
}