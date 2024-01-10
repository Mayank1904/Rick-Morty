package com.mayank.data.repository

import app.cash.turbine.test
import com.mayank.data.api.CharacterService
import com.mayank.data.fakes.FakeCharactersList
import com.mayank.data.mappers.CharacterEntityMapper
import com.mayank.data.mappers.CharacterListEntityMapper
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
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
            characterEntityMapper,
            dispatcher = Dispatchers.IO
        )
    }

    @Test
    fun `fetch characters WHEN characters api call THEN should return list`() = runTest {
        val characterList = FakeCharactersList.getCharactersList()
        val characterListModel = FakeCharactersList.getCharacterListModel()

        coEvery {
            characterService.getCharacters()
        } returns characterList
        every { characterListEntityMapper.mapFromEntity(characterList) } returns characterListModel

        characterRepositoryImpl.getCharacters().test {
            Assert.assertEquals(Result.success(characterListModel), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `GIVEN exception thrown WHEN character api call is made THEN should return exception`() =
        runTest {

            val exception = Exception()
            coEvery { characterService.getCharacters() } coAnswers {
                throw exception
            }
            characterRepositoryImpl.getCharacters().test {
                Assert.assertEquals(Result.failure<CharacterListModel>(exception), awaitItem())
                awaitComplete()
            }

        }

    @Test
    fun `GIVEN id fetch character WHEN characters api call THEN should return list`() = runTest {
        val characterItem = FakeCharactersList.getCharacter()
        val characterModel = FakeCharactersList.getCharacterModel()

        coEvery {
            characterService.getCharacter(ID)
        } returns characterItem
        every { characterEntityMapper.mapFromEntity(characterItem) } returns characterModel

        characterRepositoryImpl.getCharacter(ID).test {
            Assert.assertEquals(Result.success(characterModel), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `GIVEN exception thrown WHEN character details api call is made THEN should return exception`() =
        runTest {
            val exception = Exception()

            coEvery { characterService.getCharacter(ID) } coAnswers {
                throw exception
            }
            characterRepositoryImpl.getCharacter(ID).test {
                Assert.assertEquals(Result.failure<CharacterModel>(exception), awaitItem())
                awaitComplete()
            }

        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object {
        const val ID = 23
    }
}