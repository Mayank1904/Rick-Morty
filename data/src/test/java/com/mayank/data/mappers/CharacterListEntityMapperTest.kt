package com.mayank.data.mappers

import com.mayank.data.fakes.FakeCharactersList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharacterListEntityMapperTest {
    private var characterEntityMapper = mockk<CharacterEntityMapper>()
    private lateinit var characterListEntityMapper: CharacterListEntityMapper

    @Before
    fun setup() {
        characterListEntityMapper = CharacterListEntityMapper(characterEntityMapper)
    }

    @Test
    fun `GIVEN character list entity when mapFromEntity method called then return converted characterList`() {
        every {
            characterEntityMapper.mapFromEntity(FakeCharactersList.getCharacter())
        } returns FakeCharactersList.getCharacterModel()

        characterListEntityMapper.mapFromEntity(FakeCharactersList.getCharactersList())

        verify {
            characterEntityMapper.mapFromEntity(any())
        }
    }
}