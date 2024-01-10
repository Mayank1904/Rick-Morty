package com.mayank.domain.usecases

import com.mayank.domain.models.CharacterModel
import com.mayank.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(params: Int): Flow<Result<CharacterModel>> =
        repository.getCharacter(params)
}