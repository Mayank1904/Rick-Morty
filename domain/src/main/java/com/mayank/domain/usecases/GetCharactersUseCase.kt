package com.mayank.domain.usecases

import com.mayank.domain.Result
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): Result<CharacterListModel> = repository.getCharacters()
}