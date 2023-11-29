package com.mayank.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mayank.presentation.R

@Composable
fun CharacterDetailScreen() {

    Surface {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (characterImage, cardContainer) = createRefs()

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    .constrainAs(characterImage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, 16.dp)
                    },
            )
//            CharacterImage(
//                imageUrl = "",
//                modifier = Modifier
//                    .size(200.dp)
//                    .constrainAs(characterImage) {
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        top.linkTo(parent.top, 16.dp)
//                    },
//            )

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
                    val (characterNameText, leftContainer, rightContainer, genderLabelText, speciesLabelText, genderText, speciesText,
                        genderLocationLabelText, statusLabelText, genderLocationText, statusText) = createRefs()

                    Text(text = "Mayank",
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier
                            .padding(bottom = 25.dp)
                            .constrainAs(characterNameText) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                    )

                    Column(modifier = Modifier.constrainAs(leftContainer){
                        start.linkTo(parent.start)
                        top.linkTo(characterNameText.bottom)
                        end.linkTo(parent.end)
                    }) {
                        Text(text = "Mayank",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(text = "Mayank",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(text = "Mayank",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(text = "Mayank",
                            style = MaterialTheme.typography.body1,
                        )
                    }

                    Column(modifier = Modifier.constrainAs(rightContainer){
                        start.linkTo(parent.start)
                        top.linkTo(characterNameText.bottom)
                    }) {
                        Text(text = "Mayank",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(text = "Mayank",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(text = "Mayank",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(text = "Mayank",
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CharacterDetailScreen()
}