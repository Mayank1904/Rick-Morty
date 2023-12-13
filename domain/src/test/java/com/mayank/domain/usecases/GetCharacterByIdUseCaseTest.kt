package com.mayank.domain.usecases

import com.mayank.domain.fakes.FakeData
import com.mayank.domain.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetCharacterByIdUseCaseTest {
    private val characterRepository = mockk<CharacterRepository>()
    private lateinit var getCharacterByIdUseCaseImpl: GetCharacterByIdUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init()
        getCharacterByIdUseCaseImpl = GetCharacterByIdUseCaseImpl(characterRepository)
    }

    @Test
    fun `GIVEN id WHEN use-case invoke called THEN character detail return`() = runTest {
        val character = FakeData.getCharacter()
        coEvery { characterRepository.getCharacter(ID) } returns character

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