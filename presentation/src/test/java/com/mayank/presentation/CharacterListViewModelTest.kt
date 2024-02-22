package com.mayank.presentation

import app.cash.turbine.test
import com.mayank.domain.Result
import com.mayank.domain.usecases.GetCharactersUseCase
import com.mayank.presentation.fakes.FakeData
import com.mayank.presentation.features.homescreen.CharacterListSideEffect
import com.mayank.presentation.features.homescreen.CharacterListViewIntent
import com.mayank.presentation.features.homescreen.CharacterListViewModel
import com.mayank.presentation.features.homescreen.CharacterListViewState
import com.mayank.presentation.mappers.CharacterListMapper
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var characterListViewModel: CharacterListViewModel

    private val getCharactersUseCase = mockk<GetCharactersUseCase>()

    private val characterListMapper = mockk<CharacterListMapper>()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val data = FakeData.getCharactersList()
        coEvery { getCharactersUseCase() } returns Result.Success(FakeData.getCharacters())
        coEvery {
            characterListMapper.map(FakeData.getCharacters())
        } returns data

        characterListViewModel = CharacterListViewModel(
            getCharactersUseCase,
            characterListMapper, Dispatchers.IO, Dispatchers.Main
        )
    }

    @Test
    fun `fetch character list successfully GIVEN intent WHEN fetchCharacterList called THEN verify`() =
        runTest {
            with(characterListViewModel) {
                stateFlow.test {
                    sendIntent(CharacterListViewIntent.LoadData)

                    Assert.assertTrue(awaitItem() is CharacterListViewState.Success)

                }
            }
        }

    @Test
    fun `fetch character list failed GIVEN intent WHEN fetchCharacterList called THEN verify use-case called to get success result`() =
        runTest {
            coEvery { getCharactersUseCase() } answers {
                Result.Error(Exception())
            }

            characterListViewModel.sendIntent(CharacterListViewIntent.LoadData)

            characterListViewModel.stateFlow.test {
                Assert.assertTrue(awaitItem() is CharacterListViewState.Success)
                Assert.assertTrue(awaitItem() is CharacterListViewState.Error)
            }
        }

    @Test
    fun `navigate to details screen when CharacterListViewIntent OnCharacterClick intent passed`() =
        runTest {
            with(characterListViewModel) {
                sideEffectSharedFlow.test {
                    sendIntent(CharacterListViewIntent.OnCharacterClick(ID))

                    Assert.assertTrue(awaitItem() is CharacterListSideEffect.NavigateToDetails)
                }
            }
        }


    private companion object {
        const val ID = 1
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}