@file:OptIn(ExperimentalCoroutinesApi::class)

package com.mayank.presentation

import app.cash.turbine.test
import com.mayank.domain.usecases.GetCharactersUseCase
import com.mayank.presentation.fakes.FakeData
import com.mayank.presentation.features.homescreen.CharacterListViewIntent
import com.mayank.presentation.features.homescreen.CharacterListViewModel
import com.mayank.presentation.features.homescreen.CharacterListViewState
import com.mayank.presentation.mappers.CharacterListMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterListViewModelTest {
    private lateinit var characterListViewModel: CharacterListViewModel

    private var getCharactersUseCase = mockk<GetCharactersUseCase>()

    private var characterListMapper = mockk<CharacterListMapper>()

    private val testDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setUp() {
        MockKAnnotations.init()
        Dispatchers.setMain(testDispatcher)
        characterListViewModel = CharacterListViewModel(getCharactersUseCase, characterListMapper)
    }

    @Test
    fun `fetch character list successfully GIVEN intent WHEN fetchCharacterList called THEN verify`() = runTest {
        coEvery { getCharactersUseCase() } returns FakeData.getCharacters()

        coEvery {
            characterListMapper.mapFromModel(FakeData.getCharacters().single())
        } returns FakeData.getCharactersList()

        characterListViewModel.sendIntent(CharacterListViewIntent.LoadData)
        characterListViewModel.stateSharedFlow.test {
            Assert.assertEquals(CharacterListViewState.Success(FakeData.getCharactersList()), awaitItem())
            awaitComplete()
        }

    }
}