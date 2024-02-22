package com.mayank.presentation.mappers

import com.mayank.presentation.fakes.FakeData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterLocationMapperTest {
    private lateinit var characterLocationMapper: CharacterLocationMapper

    @Before
    fun setup() {
        characterLocationMapper = CharacterLocationMapper()
    }

    @Test
    fun `GIVEN Character Location Entity WHEN map method called THEN converted Location Model return`() {
        val characterLocation =
            FakeData.getCharacterModel().characterLocation

        val locationMapper = characterLocationMapper.map(characterLocation)

        Assert.assertEquals(locationMapper.name, LOCATION_NAME)
    }

    private companion object {
        const val LOCATION_NAME = "Earth"
    }
}