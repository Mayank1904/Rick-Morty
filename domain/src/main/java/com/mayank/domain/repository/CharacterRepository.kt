package com.mayank.domain.repository

import com.mayank.domain.Result
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(): Result<CharacterListModel>
    suspend fun getCharacter(characterId: Int): Result<CharacterModel>
}