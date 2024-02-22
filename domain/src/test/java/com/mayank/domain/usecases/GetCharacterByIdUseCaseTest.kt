package com.mayank.domain.usecases

import com.mayank.domain.Result
import com.mayank.domain.fakes.FakeData
import com.mayank.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
            coEvery { characterRepository.getCharacter(ID) } returns Result.Success(
                characterModel
            )

            // When
            val result = getCharacterByIdUseCase(ID)

            // Then
            assertTrue(result is Result.Success)
            assertEquals(characterModel, (result as Result.Success).data)
        }

    @Test
    fun `GIVEN repository throws exception WHEN invoke is called THEN return failure result`() =
        runTest {
            // Given
            val exception = Exception(ERROR)
            coEvery { characterRepository.getCharacter(ID) } returns Result.Error(exception)

            // When
            val result = getCharacterByIdUseCase(ID)

            // Then
            assertTrue(result is Result.Error)
            assertEquals(exception, (result as Result.Error).exception)
        }

    private companion object {
        const val ID = 36
        const val ERROR = "Error"
    }

}