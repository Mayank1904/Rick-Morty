package com.mayank.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mayank.presentation.R

@Composable
fun CharacterImage(imageUrl: String, modifier: Modifier) {
//    Image(
//        painter = painterResource(R.drawable.ic_launcher_background),
//        contentDescription = imageUrl
//    )
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.ic_launcher_background)
            .build(),
        contentDescription = "content",
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )

}