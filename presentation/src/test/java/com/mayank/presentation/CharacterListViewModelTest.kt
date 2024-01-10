package com.mayank.presentation

import app.cash.turbine.test
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
import kotlinx.coroutines.flow.flowOf
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

    private var getCharactersUseCase = mockk<GetCharactersUseCase>()

    private var characterListMapper = mockk<CharacterListMapper>()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        characterListViewModel = CharacterListViewModel(getCharactersUseCase, characterListMapper)
    }

    @Test
    fun `fetch character list successfully GIVEN intent WHEN fetchCharacterList called THEN verify`() =
        runTest {
            val data = FakeData.getCharactersList()
            coEvery { getCharactersUseCase() } returns flowOf(Result.success(FakeData.getCharacters()))

            coEvery {
                characterListMapper.mapFromModel(FakeData.getCharacters())
            } returns data

            characterListViewModel.sendIntent(CharacterListViewIntent.LoadData)
            Assert.assertEquals(
                CharacterListViewState.Success(data),
                characterListViewModel.stateFlow.value
            )
        }

    @Test
    fun `fetch disney characters list failed GIVEN intent WHEN fetchDisneyCharacters called THEN verify usecase called to get success result`() =
        runTest {
            coEvery { getCharactersUseCase() } answers {
                flowOf(Result.failure(Exception()))
            }

            characterListViewModel.sendIntent(CharacterListViewIntent.LoadData)

            characterListViewModel.stateFlow.test {
                Assert.assertTrue(awaitItem() is CharacterListViewState.Error)
            }
        }

    @Test
    fun `navigate to details screen when DisneyListScreenIntent NavigateToDetails intent passed`() =
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