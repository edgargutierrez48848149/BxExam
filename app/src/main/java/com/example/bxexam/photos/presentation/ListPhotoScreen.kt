package com.example.bxexam.photos.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.bxexam.photos.presentation.data.PhotoModel

@Composable
fun ListPhotoScreen(
    paddingValues: PaddingValues,
    viewModel: ViewModel = hiltViewModel(),
    navigateToDetails:(String, String) -> Unit
) {

    val photosList = viewModel.photosData.collectAsLazyPagingItems()

    when {
        photosList.loadState.refresh is LoadState.Loading && photosList.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = Color.White
                )
            }
        }

        photosList.loadState.refresh is LoadState.NotLoading && photosList.itemCount == 0 -> {
            Text(text = "Todavía no hay personajes")
        }

        photosList.loadState.hasError -> {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Red), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ha ocurrido un error")
            }
        }

        else -> {
            PhotoList(paddingValues,photosList, navigateToDetails,viewModel)

            if (photosList.loadState.append is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp), color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoList(
    paddingValues: PaddingValues,
    photosList: LazyPagingItems<PhotoModel>,
    navigateToDetails: (String,String) -> Unit,
    viewModel: ViewModel
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(photosList.itemCount) {
            photosList[it]?.let { characterModel ->
                ItemList(characterModel, navigateToDetails,viewModel)
            }
        }
    }
}

@Composable
fun ItemList(photosList: PhotoModel, navigateToDetails: (String,String) -> Unit,viewModel: ViewModel) {

    ConstraintLayout  (
        modifier = Modifier
            .clickable {
                viewModel.onLoginSelected(photosList,navigateToDetails)
            }
    ) {
        val(text,image,divider) = createRefs()

        Text(
            modifier = Modifier
                .padding(8.dp).constrainAs(text){
                start.linkTo(parent.start)
                end.linkTo(image.start)
                top.linkTo(parent.top)
                bottom.linkTo(divider.top)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            },
            text = photosList.title,
        )

        AsyncImage(
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(16))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(16))
                .constrainAs(image){
                    start.linkTo(text.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(divider.top)
                    end.linkTo(parent.end)
                },
            model = photosList.url,
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp).constrainAs(divider){
                bottom.linkTo(parent.bottom)
            },
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}
