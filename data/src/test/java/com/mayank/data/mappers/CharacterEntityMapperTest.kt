package com.mayank.data.mappers

import com.mayank.data.fakes.FakeCharactersList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterEntityMapperTest {
    private lateinit var characterEntityMapper: CharacterEntityMapper
    private val characterLocationEntityMapper = mockk<CharacterLocationEntityMapper>()

    @Before
    fun setup() {
        characterEntityMapper = CharacterEntityMapper(characterLocationEntityMapper)
    }

    @Test
    fun `GIVEN character entity WHEN map is called THEN return character model`() {
        // Given
        val characterEntity = FakeCharactersList.getCharacter()
        every {
            characterLocationEntityMapper.map(characterEntity.location)
        } returns FakeCharactersList.getCharacterModel().characterLocation

        // When
        val result = characterEntityMapper.map(characterEntity)

        // Then
        assertEquals(FakeCharactersList.getCharacterModel(), result)
        verify { characterLocationEntityMapper.map(characterEntity.location) }
    }
}