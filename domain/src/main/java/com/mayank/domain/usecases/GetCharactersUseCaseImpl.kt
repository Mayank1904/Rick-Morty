package com.mayank.domain.usecases

import com.mayank.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(private val repository: CharacterRepository) : GetCharactersUseCase {
    override suspend fun invoke() = repository.getCharacters()
}