package com.mayank.domain.repository

import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<CharacterListModel>
    suspend fun getCharacter(characterId: Int): Flow<CharacterModel>
}