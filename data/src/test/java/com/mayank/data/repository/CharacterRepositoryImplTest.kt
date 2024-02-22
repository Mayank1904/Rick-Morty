package com.mayank.data.repository

import com.mayank.data.api.CharacterService
import com.mayank.data.fakes.FakeCharactersList
import com.mayank.data.mappers.CharacterEntityMapper
import com.mayank.data.mappers.CharacterListEntityMapper
import com.mayank.domain.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryImplTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var characterRepositoryImpl: CharacterRepositoryImpl

    private val characterService = mockk<CharacterService>()
    private val characterListEntityMapper = mockk<CharacterListEntityMapper>()

    private val characterEntityMapper = mockk<CharacterEntityMapper>()

    @Before
    fun setUp() {
        characterRepositoryImpl = CharacterRepositoryImpl(
            characterService,
            characterListEntityMapper,
            characterEntityMapper
        )
    }

    @Test
    fun `GIVEN character service returns characters WHEN getCharacters is called THEN return character list model`() =
        runTest {
            // Given
            val characters = FakeCharactersList.getCharactersList()
            val characterListModel = FakeCharactersList.getCharacterListModel()
            coEvery { characterService.getCharacters() } returns characters
            every { characterListEntityMapper.map(characters) } returns characterListModel

            // When
            val result = characterRepositoryImpl.getCharacters()

            // Then
            assertTrue(result is Result.Success)
            assertEquals(characterListModel, (result as Result.Success).data)
            coVerify { characterService.getCharacters() }
            verify { characterListEntityMapper.map(characters) }
        }

    @Test
    fun `GIVEN character service throws exception WHEN getCharacters is called THEN return failure result`() =
        runTest {
            // Given
            val exception = Exception(ERROR)
            coEvery { characterService.getCharacters() } throws exception

            // When
            val result = characterRepositoryImpl.getCharacters()

            // Then
            assertTrue(result is Result.Error)
            assertEquals(exception, (result as Result.Error).exception)
            coVerify { characterService.getCharacters() }
        }

    @Test
    fun `GIVEN character service returns character WHEN getCharacter is called THEN return character model`() =
        runTest {
            // Given
            val character = FakeCharactersList.getCharacter()
            val characterModel = FakeCharactersList.getCharacterModel()
            coEvery { characterService.getCharacter(ID) } returns character
            every { characterEntityMapper.map(character) } returns characterModel

            // When
            val result = characterRepositoryImpl.getCharacter(ID)

            // Then
            assertTrue(result is Result.Success)
            assertEquals(characterModel, (result as Result.Success).data)
            coVerify { characterService.getCharacter(ID) }
            verify { characterEntityMapper.map(character) }
        }

    @Test
    fun `GIVEN character service throws exception WHEN getCharacter is called THEN return failure result`() =
        runTest {
            // Given
            val exception = RuntimeException(ERROR)
            coEvery { characterService.getCharacter(ID) } throws exception

            // When
            val result = characterRepositoryImpl.getCharacter(ID)

            // Then
            assertTrue(result is Result.Error)
            assertEquals(exception, (result as Result.Error).exception)
            coVerify { characterService.getCharacter(ID) }
        }

    private companion object {
        const val ID = 23
        const val ERROR = "Error"
    }
}