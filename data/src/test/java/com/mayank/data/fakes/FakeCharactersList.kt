package com.mayank.data.fakes

import com.mayank.data.dto.CharacterEntity
import com.mayank.data.dto.CharacterListEntity
import com.mayank.data.dto.CharacterLocationEntity
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterLocationModel
import com.mayank.domain.models.CharacterModel

object FakeCharactersList {
    fun getCharactersList() = CharacterListEntity(
        results = arrayListOf(
            CharacterEntity(
                created = "2017-11-05T09:31:08.952Z",
                gender = "unknown",
                id = 36,
                image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
                name = "Beta-Seven",
                species = "Alien",
                status = "Alive",
                type = "Hivemind",
                url = "https://rickandmortyapi.com/api/character/36",
                location = CharacterLocationEntity(
                    name = "Earth",
                    url = ""
                )
            )
        )
    )

    fun getCharacter() = CharacterEntity(
        created = "2017-11-05T09:31:08.952Z",
        gender = "unknown",
        id = 36,
        image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
        name = "Beta-Seven",
        species = "Alien",
        status = "Alive",
        type = "Hivemind",
        url = "https://rickandmortyapi.com/api/character/36",
        location = CharacterLocationEntity(
            name = "Earth",
            url = ""
        )
    )

    fun getCharacterListModel() = CharacterListModel(
        characters = arrayListOf(
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
                    name = "Earth",
                    url = ""
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
        characterLocation = CharacterLocationModel(
            name = "Earth",
            url = ""
        )
    )

}