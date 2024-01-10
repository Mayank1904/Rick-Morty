package com.mayank.presentation

import app.cash.turbine.test
import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.presentation.fakes.FakeData
import com.mayank.presentation.features.detailscreen.CharacterDetailViewIntent
import com.mayank.presentation.features.detailscreen.CharacterDetailViewModel
import com.mayank.presentation.features.detailscreen.CharacterDetailViewState
import com.mayank.presentation.mappers.CharacterMapper
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
class CharacterDetailViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private var getCharacterByIdUseCase = mockk<GetCharacterByIdUseCase>()

    private var characterMapper = mockk<CharacterMapper>()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        characterDetailViewModel =
            CharacterDetailViewModel(getCharacterByIdUseCase, characterMapper)
    }

    @Test
    fun `fetch character detail successfully GIVEN intent WHEN loadData called THEN verify`() =
        runTest {
            val data = FakeData.getCharactersList().characters[0]
            coEvery { getCharacterByIdUseCase(ID) } returns flowOf(Result.success(FakeData.getCharacter()))

            coEvery {
                characterMapper.mapFromModel(FakeData.getCharacters().characters[0])
            } returns data

            characterDetailViewModel.sendIntent(CharacterDetailViewIntent.LoadData(ID))
            Assert.assertEquals(
                CharacterDetailViewState.Success(data),
                characterDetailViewModel.stateFlow.value
            )
        }

    @Test
    fun `fetch character detail failed GIVEN intent WHEN loadData called THEN verify use-case called to get success result`() =
        runTest {
            coEvery { getCharacterByIdUseCase(ID) } answers {
                flowOf(Result.failure(Exception()))
            }

            characterDetailViewModel.sendIntent(CharacterDetailViewIntent.LoadData(ID))

            characterDetailViewModel.stateFlow.test {
                Assert.assertTrue(awaitItem() is CharacterDetailViewState.Error)
            }
        }

    private companion object {
        const val ID = 23
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}