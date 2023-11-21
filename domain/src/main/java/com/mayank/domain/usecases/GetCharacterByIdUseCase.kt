package com.mayank.domain.usecases

import com.mayank.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface GetCharacterByIdUseCase {
    suspend operator fun invoke(params: Int) : Flow<CharacterModel>
}