package com.mayank.data.repository

import com.mayank.data.api.CharacterService
import com.mayank.data.mappers.CharacterEntityMapper
import com.mayank.data.mappers.CharacterListEntityMapper
import com.mayank.domain.Result
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.models.CharacterModel
import com.mayank.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
    private val characterListEntityMapper: CharacterListEntityMapper,
    private val characterEntityMapper: CharacterEntityMapper,
    private val dispatcher: CoroutineDispatcher
) : CharacterRepository {

    override suspend fun getCharacters(): Result<CharacterListModel> =
        withContext(dispatcher) {
            try {
                characterService.getCharacters().run {
                    Result.Success(characterListEntityMapper.map(this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getCharacter(characterId: Int): Result<CharacterModel> =
        withContext(dispatcher) {
            try {
                characterService.getCharacter(characterId).run {
                    Result.Success(characterEntityMapper.map(this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}
