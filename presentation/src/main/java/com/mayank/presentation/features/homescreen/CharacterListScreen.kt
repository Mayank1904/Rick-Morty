package com.mayank.presentation.features.homescreen

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mayank.presentation.R
import com.mayank.presentation.components.CharacterCard
import com.mayank.presentation.components.ProgressBar
import com.mayank.presentation.models.CharacterItem

@Composable
fun CharacterListScreen(context: Context, callback: (id: Int) -> Unit) {
    val viewModel: CharacterListViewModel = hiltViewModel()
    var apiCalled by rememberSaveable { mutableStateOf(false) }
    if (!apiCalled) {
        LaunchedEffect(Unit) {
            viewModel.sendIntent(CharacterListViewIntent.LoadData)
        }
        apiCalled = true
    }

    val viewState =
        viewModel.stateFlow.collectAsState(0)

    when (viewState.value) {
        is CharacterListViewState.Loading ->
            ProgressBar(modifier = Modifier.fillMaxSize())

        is CharacterListViewState.Success ->
            CharacterList(
                characterList = (viewState.value as CharacterListViewState.Success).data.characters,
                onItemClicked = {
                    viewModel.sendIntent(CharacterListViewIntent.OnCharacterClick(it.id))
                }
            )

        is CharacterListViewState.Error -> Toast.makeText(
            context,
            stringResource(R.string.no_network_connectivity), LENGTH_LONG
        ).show()
    }

    val sideEffect = viewModel.sideEffectSharedFlow.collectAsState(initial = 0)
    when (sideEffect.value) {
        is CharacterListSideEffect.NavigateToDetails ->
            LaunchedEffect(Unit) {
                callback((sideEffect.value as CharacterListSideEffect.NavigateToDetails).id)
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