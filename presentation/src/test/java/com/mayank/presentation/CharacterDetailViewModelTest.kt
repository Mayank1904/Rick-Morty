package com.mayank.presentation

import android.net.http.HttpException
import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.presentation.fakes.FakeData
import com.mayank.presentation.features.detailscreen.CharacterDetailViewIntent
import com.mayank.presentation.features.detailscreen.CharacterDetailViewModel
import com.mayank.presentation.features.detailscreen.CharacterDetailViewState
import com.mayank.presentation.mappers.CharacterMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {
    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private var getCharacterByIdUseCase = mockk<GetCharacterByIdUseCase>()

    private var characterMapper = mockk<CharacterMapper>()

    private val testDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setUp() {
        MockKAnnotations.init()
        Dispatchers.setMain(testDispatcher)
        characterDetailViewModel = CharacterDetailViewModel(getCharacterByIdUseCase, characterMapper)
    }

    @Test
    fun `fetch character detail successfully GIVEN intent WHEN loadData called THEN verify`() = runTest {
        val data = FakeData.getCharactersList().characters[0]
        coEvery { getCharacterByIdUseCase(ID) } returns FakeData.getCharacter()

        coEvery {
            characterMapper.mapFromModel(FakeData.getCharacters().single().characters[0])
        } returns data

        characterDetailViewModel.sendIntent(CharacterDetailViewIntent.LoadData(ID))
        Assert.assertEquals(CharacterDetailViewState.Success(data), characterDetailViewModel.stateFlow.value)
    }

    @Test(expected = HttpException::class, timeout = 1L)
    fun `fetch character detail should return error from use-case`() =
        runTest {
            coEvery {
                getCharacterByIdUseCase(ID)
            } answers { throw HttpException(ERROR_MESSAGE, Throwable()) }
            characterDetailViewModel.sendIntent(CharacterDetailViewIntent.LoadData(ID))
        }

    private companion object {
        const val ERROR_MESSAGE = "Internal Server Error"
        const val ID = 23
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}