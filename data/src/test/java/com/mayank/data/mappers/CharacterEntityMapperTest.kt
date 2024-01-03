package com.mayank.data.mappers

import com.mayank.data.fakes.FakeCharactersList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharacterEntityMapperTest {
    private lateinit var characterEntityMapper : CharacterEntityMapper
    private val characterLocationEntityMapper = mockk<CharacterLocationEntityMapper>()

    @Before
    fun setup(){
        characterEntityMapper = CharacterEntityMapper(characterLocationEntityMapper)
    }

    @Test
    fun `GIVEN character list entity when mapFromEntity method called then return converted characterList`() {
        every {
            characterLocationEntityMapper.mapFromEntity(FakeCharactersList.getCharactersList().results[0].location)
        } returns FakeCharactersList.getCharacterModel().characterLocation

        characterEntityMapper.mapFromEntity(FakeCharactersList.getCharacter())

        verify {
            characterLocationEntityMapper.mapFromEntity(any())
        }
    }
}