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
                created = CREATED_DATE,
                gender = GENDER,
                id = ID,
                image = IMAGE,
                name = NAME,
                species = SPECIES,
                status = STATUS,
                type = TYPE,
                url = URL,
                location = CharacterLocationEntity(
                    name = LOCATION_NAME,
                    url = EMPTY_STRING
                )
            )
        )
    )

    fun getCharacter() = CharacterEntity(
        created = CREATED_DATE,
        gender = GENDER,
        id = ID,
        image = IMAGE,
        name = NAME,
        species = SPECIES,
        status = STATUS,
        type = TYPE,
        url = URL,
        location = CharacterLocationEntity(
            name = LOCATION_NAME,
            url = EMPTY_STRING
        )
    )

    fun getCharacterListModel() = CharacterListModel(
        characters = arrayListOf(
            CharacterModel(
                created = CREATED_DATE,
                gender = GENDER,
                id = ID,
                image = IMAGE,
                name = NAME,
                species = SPECIES,
                status = STATUS,
                type = TYPE,
                url = URL,
                characterLocation = CharacterLocationModel(
                    name = LOCATION_NAME,
                    url = EMPTY_STRING
                )
            )
        )
    )

    fun getCharacterModel() = CharacterModel(
        created = CREATED_DATE,
        gender = GENDER,
        id = ID,
        image = IMAGE,
        name = NAME,
        species = SPECIES,
        status = STATUS,
        type = TYPE,
        url = URL,
        characterLocation = CharacterLocationModel(
            name = LOCATION_NAME,
            url = EMPTY_STRING
        )
    )

    private const val CREATED_DATE = "2017-11-05T09:31:08.952Z"
    private const val GENDER = "unknown"
    private const val ID = 36
    private const val IMAGE = "https://rickandmortyapi.com/api/character/avatar/36.jpeg"
    private const val NAME = "Beta-Seven"
    private const val SPECIES = "Alien"
    private const val STATUS = "Alive"
    private const val TYPE = "Hivemind"
    private const val URL = "https://rickandmortyapi.com/api/character/36"
    private const val LOCATION_NAME = "Earth"
    private const val EMPTY_STRING = ""

}