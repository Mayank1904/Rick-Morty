package com.mayank.domain.usecases

import com.mayank.domain.fakes.FakeData
import com.mayank.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
    fun `GIVEN repository returns character list model WHEN invoke is called THEN return character list model`() =
        runTest {
            // Given
            val characterListModel = FakeData.getCharacters()

            coEvery { characterRepository.getCharacters() } returns flowOf(
                Result.success(
                    characterListModel
                )
            )

            // When
            val result = getCharactersUseCase().single()

            // Then
            assertTrue(result.isSuccess)
            assertEquals(characterListModel, result.getOrNull())
            coVerify { characterRepository.getCharacters() }
        }

    @Test
    fun `GIVEN repository throws exception WHEN invoke is called THEN return failure result`() =
        runTest {
            // Given
            val exception = Exception("Error")

            coEvery { characterRepository.getCharacters() } returns flowOf(Result.failure(exception))

            // When
            val result = getCharactersUseCase().single()

            // Then
            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            coVerify { characterRepository.getCharacters() }
        }

}