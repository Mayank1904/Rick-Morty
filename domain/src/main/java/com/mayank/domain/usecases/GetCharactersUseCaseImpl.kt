package com.mayank.domain.usecases

import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(private val repository: CharacterRepository) : GetCharactersUseCase {
    override suspend fun invoke(): Flow<CharacterListModel> = repository.getCharacters()
}