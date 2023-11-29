package com.mayank.presentation.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mayank.presentation.R

@Composable
fun BaseScreen(
    title: String,
    showBackButton: Boolean,
    onBackClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (showBackButton) {
                            IconButton(onClick = { onBackClicked.invoke() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = null)
                            }
                        }
                        Text(
                            text = title
                        )
                    }
                },
                contentColor = Color.White,
            )
        },
    ) {
        Surface(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    BaseScreen(title = stringResource(id = R.string.characters), showBackButton = true, onBackClicked = { /*TODO*/ }) {
        Text(text = "Hello")
    }
}