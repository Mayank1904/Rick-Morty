package com.mayank.domain.repository

import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<Result<CharacterListModel>> {
        return emptyFlow()
    }

    suspend fun getCharacter(characterId: Int): Flow<Result<CharacterModel>>
}