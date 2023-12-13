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
    fun `GIVEN Character Location Entity WHEN mapFromEntity method called THEN converted Location Model return`()
        {
            val characterLocation =
                FakeCharactersList.getCharactersList().characters[0].characterLocation

            val locationMapper = characterLocationEntityMapper.mapFromEntity(characterLocation)

            Assert.assertEquals(locationMapper.name, LOCATION_NAME)
        }

    private companion object {
        const val LOCATION_NAME = "Earth"
    }
}