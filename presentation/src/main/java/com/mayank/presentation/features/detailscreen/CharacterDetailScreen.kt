package com.mayank.presentation.features.detailscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.mayank.presentation.R
import com.mayank.presentation.components.CharacterImage
import com.mayank.presentation.components.ProgressBar
import com.mayank.presentation.models.CharacterItem

@Composable
fun CharacterDetailScreen(id: Int, context: Context) {
    val viewModel: CharacterDetailViewModel = hiltViewModel()
    var apiCalled by rememberSaveable { mutableStateOf(false) }
    if (!apiCalled) {
        LaunchedEffect(Unit) {
            viewModel.sendIntent(CharacterDetailViewIntent.LoadData(id))
        }
        apiCalled = true
    }
    val viewState = viewModel.stateFlow.collectAsState(initial = CharacterDetailViewState.Loading)

    when (viewState.value) {
        is CharacterDetailViewState.Loading ->
            ProgressBar(modifier = Modifier.fillMaxSize())

        is CharacterDetailViewState.Success ->
            DetailScreen((viewState.value as CharacterDetailViewState.Success).data)

        is CharacterDetailViewState.Error -> Toast.makeText(
            context,
            stringResource(R.string.no_network_connectivity), Toast.LENGTH_LONG
        ).show()
    }

}

@Composable
fun DetailScreen(data: CharacterItem) {
    Surface {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            val (characterImage, cardContainer) = createRefs()
            CharacterImage(
                imageUrl = data.image,
                modifier = Modifier
                    .size(200.dp)
                    .constrainAs(characterImage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, 16.dp)
                    },
            )

            Card(
                modifier = Modifier.constrainAs(
                    cardContainer
                ) {
                    top.linkTo(characterImage.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
                elevation = 8.dp,
            ) {
                ConstraintLayout(modifier = Modifier.padding(16.dp)) {
                    val (characterNameText, leftContainer, rightContainer) = createRefs()

                    Text(text = data.name,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier
                            .padding(bottom = 25.dp)
                            .constrainAs(characterNameText) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                    )

                    Column(modifier = Modifier.constrainAs(leftContainer) {
                        start.linkTo(parent.start)
                        top.linkTo(characterNameText.bottom)
                        end.linkTo(parent.end)
                    }) {
                        Text(
                            text = stringResource(R.string.gender),
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = data.gender,
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = stringResource(R.string.location),
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = data.characterLocation.name,
                            style = MaterialTheme.typography.body1,
                        )
                    }

                    Column(modifier = Modifier.constrainAs(rightContainer) {
                        start.linkTo(parent.start)
                        top.linkTo(characterNameText.bottom)
                    }) {
                        Text(
                            text = stringResource(R.string.species),
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = data.species,
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = stringResource(R.string.status),
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = data.status,
                            style = MaterialTheme.typography.body1,
                        )
                    }

                    createHorizontalChain(
                        leftContainer, rightContainer,
                        chainStyle = ChainStyle.SpreadInside
                    )

                }
            }

        }
    }
}