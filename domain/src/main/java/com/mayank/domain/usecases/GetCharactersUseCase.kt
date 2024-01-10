package com.mayank.domain.usecases

import com.mayank.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke() = repository.getCharacters()
}