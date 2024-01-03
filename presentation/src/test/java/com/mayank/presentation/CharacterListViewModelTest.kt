package com.mayank.presentation

import android.net.http.HttpException
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
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
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
        val data = FakeData.getCharactersList()
        coEvery { getCharactersUseCase() } returns FakeData.getCharacters()

        coEvery {
            characterListMapper.mapFromModel(FakeData.getCharacters().single())
        } returns data

        characterListViewModel.sendIntent(CharacterListViewIntent.LoadData)
        Assert.assertEquals(CharacterListViewState.Success(data), characterListViewModel.stateFlow.value)
    }

    @Test(expected = HttpException::class)
    fun `fetch character list should return error from use-case`() =
        runTest {
            coEvery {
                getCharactersUseCase()
            } answers { throw HttpException(ERROR_MESSAGE, Throwable()) }
            characterListViewModel.sendIntent(CharacterListViewIntent.LoadData)
        }

    private companion object {
        const val ERROR_MESSAGE = "Internal Server Error"
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}