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
class GetCharacterByIdUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)
    private val characterRepository = mockk<CharacterRepository>()
    private lateinit var getCharacterByIdUseCaseImpl: GetCharacterByIdUseCase

    @Before
    fun setup() {
        getCharacterByIdUseCaseImpl = GetCharacterByIdUseCase(characterRepository)
    }

    @Test
    fun `GIVEN id WHEN use-case invoke called THEN character detail return`() = runTest {
        val character = FakeData.getCharacter()
        coEvery { characterRepository.getCharacter(ID) } returns flowOf(Result.success(character))

        getCharacterByIdUseCaseImpl(ID)

        verify(times(1)) {
            characterRepository.getCharacter(ID)
        }
    }

    @Test(expected = IOException::class)
    fun `GIVEN id WHEN use-case invoke called THEN exception thrown`() = runTest {
        coEvery { characterRepository.getCharacter(ID) } answers {
            throw IOException()
        }

        getCharacterByIdUseCaseImpl(ID)

        verify(times(1)) {
            characterRepository.getCharacter(ID)
        }

    }

    private companion object {
        const val ID = 36
    }

}