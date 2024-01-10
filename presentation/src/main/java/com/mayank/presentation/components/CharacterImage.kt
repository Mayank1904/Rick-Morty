package com.mayank.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mayank.presentation.R

@Composable
fun CharacterImage(imageUrl: String, modifier: Modifier) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.ic_launcher_background)
            .build(),
        contentDescription = stringResource(R.string.content),
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )

}