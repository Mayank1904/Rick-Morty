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
import com.mayank.presentation.components.ErrorScreen
import com.mayank.presentation.components.ProgressBar
import com.mayank.presentation.models.CharacterItem

@Composable
fun CharacterListScreen(callback: (id: Int) -> Unit) {
    val viewModel: CharacterListViewModel = hiltViewModel()

    val viewState =
        viewModel.stateFlow.collectAsState(initial = CharacterListViewState.Loading)

    when (val viewStateValue = viewState.value) {
        is CharacterListViewState.Loading ->
            ProgressBar(modifier = Modifier.fillMaxSize())

        is CharacterListViewState.Success -> {
            CharacterList(
                characterList = viewStateValue.data.characters,
                onItemClicked = {
                    viewModel.sendIntent(CharacterListViewIntent.OnCharacterClick(it.id))
                }
            )
        }

        is CharacterListViewState.Error -> ErrorScreen()
    }
    LaunchedEffect(Unit) {
        viewModel.sideEffectSharedFlow.collect {
            when (it) {
                is CharacterListSideEffect.NavigateToDetails -> callback(it.id)
            }
        }
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