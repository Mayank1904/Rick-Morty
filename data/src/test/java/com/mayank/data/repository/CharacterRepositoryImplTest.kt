package com.mayank.data.repository

import app.cash.turbine.test
import com.mayank.data.api.CharacterService
import com.mayank.data.fakes.FakeCharactersList
import com.mayank.data.mappers.CharacterEntityMapper
import com.mayank.data.mappers.CharacterListEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryImplTest {
    private lateinit var characterRepositoryImpl: CharacterRepositoryImpl

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var characterService: CharacterService
    @MockK
    private lateinit var characterListEntityMapper: CharacterListEntityMapper
    @MockK
    private lateinit var characterEntityMapper: CharacterEntityMapper

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        characterRepositoryImpl = CharacterRepositoryImpl(
            characterService,
            characterListEntityMapper,
            characterEntityMapper
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
            Assert.assertEquals(characterListModel, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `GIVEN exception thrown WHEN character api call is made THEN should return exception`() = runTest {
        val exception = retrofit2.HttpException(
            Response.error<ResponseBody>(
                503,
                "Address no found".toResponseBody("plain/text".toMediaTypeOrNull())
            )
        )

        coEvery { characterService.getCharacters() } coAnswers {
            throw exception
        }
        characterRepositoryImpl.getCharacters().test {
            Assert.assertEquals(exception, awaitError())
            awaitError()
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
            Assert.assertEquals(characterModel, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `GIVEN exception thrown WHEN character details api call is made THEN should return exception`() = runTest {
        val exception = retrofit2.HttpException(
            Response.error<ResponseBody>(
                503,
                "Address no found".toResponseBody("plain/text".toMediaTypeOrNull())
            )
        )

        coEvery { characterService.getCharacters() } coAnswers {
            throw exception
        }
        characterRepositoryImpl.getCharacters().test {
            Assert.assertEquals(exception, awaitError())
            awaitError()
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