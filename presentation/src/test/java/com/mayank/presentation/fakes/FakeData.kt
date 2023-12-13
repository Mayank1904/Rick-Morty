package com.mayank.presentation.fakes

import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterLocationModel
import com.mayank.domain.models.CharacterModel
import com.mayank.presentation.models.CharacterItem
import com.mayank.presentation.models.CharacterList
import com.mayank.presentation.models.CharacterLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getCharacters(): Flow<CharacterListModel> = flow {
        val characters = CharacterListModel(
            characters = listOf<CharacterModel>(
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
        emit(characters)
    }

    fun getCharactersList() = CharacterList(
        characters = arrayListOf(
            CharacterItem(
                created = "2017-11-05T09:31:08.952Z",
                gender = "unknown",
                id = 36,
                image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
                name = "Beta-Seven",
                species = "Alien",
                status = "Alive",
                type = "Hivemind",
                url = "https://rickandmortyapi.com/api/character/36",
                characterLocation = CharacterLocation(
                    name = "Earth",
                )
            )
        )
    )
}