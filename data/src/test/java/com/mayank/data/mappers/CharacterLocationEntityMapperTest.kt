package com.mayank.data.mappers

import com.mayank.data.fakes.FakeCharactersList
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterLocationEntityMapperTest {
    private lateinit var characterLocationEntityMapper: CharacterLocationEntityMapper

    @Before
    fun setup() {
        characterLocationEntityMapper = CharacterLocationEntityMapper()
    }

    @Test
    fun `GIVEN character location entity WHEN mapFromEntity is called THEN return character location model`() {
        // Given
        val characterLocation =
            FakeCharactersList.getCharactersList().results[0].location
        // When
        val locationMapper = characterLocationEntityMapper.mapFromEntity(characterLocation)
        // Then
        Assert.assertEquals(locationMapper.name, LOCATION_NAME)
    }

    private companion object {
        const val LOCATION_NAME = "Earth"
    }
}