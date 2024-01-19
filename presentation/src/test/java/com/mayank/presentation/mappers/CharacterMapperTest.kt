package com.mayank.presentation.mappers

import com.mayank.presentation.fakes.FakeData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharacterMapperTest {
    private lateinit var characterMapper: CharacterMapper
    private val characterLocationMapper = mockk<CharacterLocationMapper>()

    @Before
    fun setup() {
        characterMapper = CharacterMapper(characterLocationMapper)
    }

    @Test
    fun `GIVEN character list entity when map method called then return converted characterList`() {
        every {
            characterLocationMapper.map(FakeData.getCharacterLocationModel())
        } returns FakeData.getCharactersList().characters[0].characterLocation

        characterMapper.map(FakeData.getCharacterModel())

        verify {
            characterLocationMapper.map(any())
        }
    }
}