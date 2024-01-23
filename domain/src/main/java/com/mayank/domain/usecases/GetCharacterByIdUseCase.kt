package com.mayank.domain.usecases

import com.mayank.domain.Result
import com.mayank.domain.models.CharacterModel
import com.mayank.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(params: Int): Result<CharacterModel> =
        repository.getCharacter(params)
}