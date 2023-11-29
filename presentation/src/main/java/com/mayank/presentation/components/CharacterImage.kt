package com.mayank.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
        modifier = Modifier.size(72.dp),
        contentScale = ContentScale.Crop,
    )

}