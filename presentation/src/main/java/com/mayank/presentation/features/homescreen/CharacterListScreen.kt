package com.mayank.presentation.features.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mayank.presentation.components.CharacterCard
import com.mayank.presentation.components.ProgressBar
import com.mayank.presentation.models.CharacterItem

@Composable
fun CharacterListScreen(callback: (id: Int) -> Unit) {
    val viewModel: CharacterListViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.sendIntent(CharacterListViewIntent.LoadData)
    }

    val viewState =
        viewModel.stateFlow.collectAsState(initial = CharacterListViewState.Loading)

    when (viewState.value) {
        is CharacterListViewState.Loading -> {
            ProgressBar(modifier = Modifier.fillMaxSize())
        }

        is CharacterListViewState.Success -> {
            CharacterList(
                characterList = (viewState.value as CharacterListViewState.Success).data.characters,
                onItemClicked = {
                    callback.invoke(it.id)
                }
            )
        }

        is CharacterListViewState.Error -> {}
    }
}

@Composable
fun CharacterList(
    characterList: List<CharacterItem>,
    onItemClicked: (CharacterItem) -> Unit
) {
    LazyColumn {
        items(characterList) { characterItem ->
            CharacterCard(characterItem, onItemClicked)
        }
    }
}