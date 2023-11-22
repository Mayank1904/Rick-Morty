package com.mayank.domain.usecases

import com.mayank.domain.models.CharacterListModel
import kotlinx.coroutines.flow.Flow

interface CharacterListUseCase {
    suspend operator fun invoke() : Flow<CharacterListModel>
}