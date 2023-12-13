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

class GetCharactersUseCaseTest {

    private val characterRepository = mockk<CharacterRepository>()
    private lateinit var getCharactersUseCaseImpl: GetCharactersUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCharactersUseCaseImpl = GetCharactersUseCaseImpl(characterRepository)
    }

    @Test
    fun `GIVEN no data WHEN use-case invoke called THEN character list return`() = runTest {
        val characters = FakeData.getCharacters()
        coEvery { characterRepository.getCharacters() } returns characters

        getCharactersUseCaseImpl()

        verify(times(1)) {
            characterRepository.getCharacters()
        }
    }

    @Test(expected = IOException::class)
    fun `GIVEN no data WHEN use-case invoke called THEN exception thrown`() = runTest {
        coEvery { characterRepository.getCharacters() } answers {
            throw IOException()
        }

        getCharactersUseCaseImpl()

        verify(times(1)) {
            characterRepository.getCharacters()
        }

    }

}