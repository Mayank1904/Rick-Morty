package com.mayank.presentation.mappers

import com.mayank.presentation.fakes.FakeData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharacterListMapperTest {
    private var characterMapper = mockk<CharacterMapper>()
    private lateinit var characterListMapper: CharacterListMapper

    @Before
    fun setup() {
        characterListMapper = CharacterListMapper(characterMapper)
    }

    @Test
    fun `GIVEN character model when mapFromModel method called then return converted characterItem`() {
        every {
            characterMapper.mapFromModel(FakeData.getCharacterModel())
        } returns FakeData.getCharactersList().characters[0]

        characterListMapper.mapFromModel(FakeData.getCharacterListModel())

        verify {
            characterMapper.mapFromModel(any())
        }
    }
}