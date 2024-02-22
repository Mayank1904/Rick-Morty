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
                gender = GENDER,
                id = ID,
                image = IMAGE,
                name = NAME,
                species = SPECIES,
                status = STATUS,
                characterLocation = CharacterLocation(
                    name = LOCATION_NAME,
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
        characterLocation = getCharacterLocationModel()
    )

    fun getCharacterListModel() = CharacterListModel(
        characters = listOf(
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
                characterLocation = getCharacterLocationModel()
            )
        )
    )

    fun getCharacterLocationModel() = CharacterLocationModel(
        name = LOCATION_NAME,
        url = EMPTY_STRING
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