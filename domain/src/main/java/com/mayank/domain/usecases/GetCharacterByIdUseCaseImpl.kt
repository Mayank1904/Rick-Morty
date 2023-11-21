package com.mayank.domain.usecases

import com.mayank.domain.models.CharacterModel
import com.mayank.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterByIdUseCaseImpl @Inject constructor(private val repository: CharacterRepository) : GetCharacterByIdUseCase {
    override suspend operator fun invoke(params: Int): Flow<CharacterModel> = repository.getCharacter(params)
}