package com.mayank.domain.fakes

import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterLocationModel
import com.mayank.domain.models.CharacterModel

object FakeData {
    fun getCharacters(): CharacterListModel = CharacterListModel(
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
                characterLocation = CharacterLocationModel(
                    name = "unknown",
                    url = ""
                )
            )
        )
    )

    fun getCharacter(): CharacterModel = CharacterModel(

        created = "2017-11-05T09:31:08.952Z",
        gender = "unknown",
        id = 36,
        image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
        name = "Beta-Seven",
        species = "Alien",
        status = "Alive",
        type = "Hivemind",
        url = "https://rickandmortyapi.com/api/character/36",
        characterLocation = CharacterLocationModel(
            name = "unknown",
            url = ""
        )

    )
}