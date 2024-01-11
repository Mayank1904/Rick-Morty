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
class GetCharacterByIdUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)
    private val characterRepository = mockk<CharacterRepository>()
    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @Before
    fun setup() {
        getCharacterByIdUseCase = GetCharacterByIdUseCase(characterRepository)
    }

    @Test
    fun `GIVEN repository returns character model WHEN invoke is called THEN return character model`() =
        runTest {
            // Given
            val characterModel = FakeData.getCharacter()

            coEvery { characterRepository.getCharacter(ID) } returns flowOf(
                Result.success(
                    characterModel
                )
            )

            // When
            val result = getCharacterByIdUseCase(ID).single()

            // Then
            assertTrue(result.isSuccess)
            assertEquals(characterModel, result.getOrNull())
            coVerify { characterRepository.getCharacter(ID) }
        }

    @Test
    fun `GIVEN repository throws exception WHEN invoke is called THEN return failure result`() =
        runTest {
            // Given
            val exception = Exception("Error")

            coEvery { characterRepository.getCharacter(ID) } returns flowOf(Result.failure(exception))

            // When
            val result = getCharacterByIdUseCase(ID).single()

            // Then
            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            coVerify { characterRepository.getCharacter(ID) }
        }

    private companion object {
        const val ID = 36
    }

}