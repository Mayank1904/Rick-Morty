@file:OptIn(ExperimentalMaterialApi::class)

package com.mayank.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mayank.presentation.R
import com.mayank.presentation.models.CharacterItem

@Composable
fun CharacterCard(characterItem: CharacterItem, onItemClicked: (CharacterItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        onClick = {
            onItemClicked.invoke(characterItem)
        }

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            CharacterImage(characterItem.image, modifier = Modifier)
            CharacterContent(characterItem)
        }
    }
}

@Composable
fun CharacterContent(characterItem: CharacterItem) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = characterItem.name,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "${characterItem.status} - ${characterItem.species}",
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            text = stringResource(R.string.last_known_parents),
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            text = characterItem.characterLocation.name,
            style = MaterialTheme.typography.body1
        )

    }
}