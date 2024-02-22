package com.mayank.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.mayank.domain.Result
import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.presentation.fakes.FakeData
import com.mayank.presentation.features.detailscreen.CharacterDetailViewIntent
import com.mayank.presentation.features.detailscreen.CharacterDetailViewModel
import com.mayank.presentation.features.detailscreen.CharacterDetailViewState
import com.mayank.presentation.mappers.CharacterMapper
import io.mockk.coEvery
import io.mockk.every
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
class CharacterDetailViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private val getCharacterByIdUseCase = mockk<GetCharacterByIdUseCase>()

    private val characterMapper = mockk<CharacterMapper>()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { savedStateHandle.get<Int>(characterId) } returns ID
        val data = FakeData.getCharactersList().characters[0]
        coEvery { getCharacterByIdUseCase(ID) } returns Result.Success(FakeData.getCharacter())

        coEvery {
            characterMapper.map(FakeData.getCharacters().characters[0])
        } returns data
        characterDetailViewModel =
            CharacterDetailViewModel(getCharacterByIdUseCase, characterMapper, savedStateHandle, Dispatchers.IO, Dispatchers.Main)
    }

    @Test
    fun `fetch character detail successfully GIVEN intent WHEN loadData called THEN verify`() =
        runTest {
            with(characterDetailViewModel) {
                stateFlow.test {
                    sendIntent(CharacterDetailViewIntent.LoadData(ID))

                    Assert.assertTrue(awaitItem() is CharacterDetailViewState.Success)
                }
            }
        }

    @Test
    fun `fetch character detail failed GIVEN intent WHEN loadData called THEN verify use-case called to get success result`() =
        runTest {
            coEvery { getCharacterByIdUseCase(ID) } answers {
                Result.Error(Exception())
            }

            with(characterDetailViewModel) {
                sendIntent(CharacterDetailViewIntent.LoadData(ID))

                stateFlow.test {
                    Assert.assertTrue(
                        awaitItem() is CharacterDetailViewState.Error
                    )
                }

            }

        }

    private companion object {
        const val ID = 23
        const val characterId = "characterId"
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}