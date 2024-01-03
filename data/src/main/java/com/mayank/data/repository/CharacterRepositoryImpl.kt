package com.mayank.data.repository

import com.mayank.data.api.CharacterService
import com.mayank.data.mappers.CharacterEntityMapper
import com.mayank.data.mappers.CharacterListEntityMapper
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterModel
import com.mayank.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
    private val characterListEntityMapper: CharacterListEntityMapper,
    private val characterEntityMapper: CharacterEntityMapper
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<CharacterListModel> = flow {
        val characters = characterService.getCharacters()
        emit(characterListEntityMapper.mapFromEntity(characters))
    }.flowOn(Dispatchers.IO)

    override suspend fun getCharacter(characterId: Int): Flow<CharacterModel> = flow {
        val character = characterService.getCharacter(characterId)
        emit(characterEntityMapper.mapFromEntity(character))
    }.flowOn(Dispatchers.IO)
}
