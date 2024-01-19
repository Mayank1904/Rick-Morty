package com.mayank.data.repository

import com.mayank.data.api.CharacterService
import com.mayank.data.mappers.CharacterEntityMapper
import com.mayank.data.mappers.CharacterListEntityMapper
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterModel
import com.mayank.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
    private val characterListEntityMapper: CharacterListEntityMapper,
    private val characterEntityMapper: CharacterEntityMapper,
    private val dispatcher: CoroutineDispatcher
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<Result<CharacterListModel>> = flow {
        try {
            characterService.getCharacters().run {
                emit(Result.success(characterListEntityMapper.map(this)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(dispatcher)

    override suspend fun getCharacter(characterId: Int): Flow<Result<CharacterModel>> = flow {
        try {
            characterService.getCharacter(characterId).run {
                emit(Result.success(characterEntityMapper.map(this)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(dispatcher)
}
