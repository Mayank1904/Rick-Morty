package com.mayank.domain.usecases

import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): Flow<Result<CharacterListModel>> = repository.getCharacters()
}