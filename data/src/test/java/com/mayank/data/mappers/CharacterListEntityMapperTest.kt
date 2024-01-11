package com.mayank.data.mappers

import com.mayank.data.fakes.FakeCharactersList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
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
    fun `GIVEN character list entity WHEN mapFromEntity is called THEN return character list model`() {
        // Given
        val characterListEntity = FakeCharactersList.getCharactersList()

        every {
            characterEntityMapper.mapFromEntity(any())
        } returns FakeCharactersList.getCharacterModel()

        // When
        val result = characterListEntityMapper.mapFromEntity(characterListEntity)

        // Then
        assertEquals(FakeCharactersList.getCharacterListModel(), result)
        verify(exactly = characterListEntity.results.size) {
            characterEntityMapper.mapFromEntity(any())
        }
    }
}