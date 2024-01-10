package com.mayank.domain.usecases

import com.mayank.domain.fakes.FakeData
import com.mayank.domain.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharactersUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val characterRepository = mockk<CharacterRepository>()
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCase(characterRepository)
    }

    @Test
    fun `GIVEN no data WHEN use-case invoke called THEN character list return`() = runTest {
        val characters = FakeData.getCharacters()
        coEvery { characterRepository.getCharacters() } returns flowOf(Result.success(characters))

        getCharactersUseCase()

        verify(times(1)) {
            characterRepository.getCharacters()
        }
    }

    @Test(expected = IOException::class)
    fun `GIVEN no data WHEN use-case invoke called THEN exception thrown`() = runTest {
        coEvery { characterRepository.getCharacters() } answers {
            throw IOException()
        }

        getCharactersUseCase()

        verify(times(1)) {
            characterRepository.getCharacters()
        }

    }

}