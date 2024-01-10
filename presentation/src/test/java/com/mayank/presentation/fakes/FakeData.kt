package com.mayank.presentation.fakes

import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterLocationModel
import com.mayank.domain.models.CharacterModel
import com.mayank.presentation.models.CharacterItem
import com.mayank.presentation.models.CharacterList
import com.mayank.presentation.models.CharacterLocation

object FakeData {
    fun getCharacters(): CharacterListModel = getCharacterListModel()

    fun getCharacter(): CharacterModel = getCharacterModel()

    fun getCharactersList() = CharacterList(
        characters = arrayListOf(
            CharacterItem(
                gender = "unknown",
                id = 36,
                image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
                name = "Beta-Seven",
                species = "Alien",
                status = "Alive",
                characterLocation = CharacterLocation(
                    name = "Earth",
                )
            )
        )
    )

    fun getCharacterModel() = CharacterModel(
        created = "2017-11-05T09:31:08.952Z",
        gender = "unknown",
        id = 36,
        image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
        name = "Beta-Seven",
        species = "Alien",
        status = "Alive",
        type = "Hivemind",
        url = "https://rickandmortyapi.com/api/character/36",
        characterLocation = getCharacterLocationModel()
    )

    fun getCharacterListModel() = CharacterListModel(
        characters = listOf(
            CharacterModel(
                created = "2017-11-05T09:31:08.952Z",
                gender = "unknown",
                id = 36,
                image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
                name = "Beta-Seven",
                species = "Alien",
                status = "Alive",
                type = "Hivemind",
                url = "https://rickandmortyapi.com/api/character/36",
                characterLocation = getCharacterLocationModel()
            )
        )
    )

    fun getCharacterLocationModel() = CharacterLocationModel(
        name = "unknown",
        url = ""
    )

}