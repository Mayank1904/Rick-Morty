package com.mayank.data.di

import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.domain.usecases.GetCharacterByIdUseCaseImpl
import com.mayank.domain.usecases.GetCharactersUseCase
import com.mayank.domain.usecases.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindCharacterListUseCase(characterListUseCaseImpl: GetCharactersUseCaseImpl) : GetCharactersUseCase

    @Binds
    abstract fun bindGetCharacterByIdUseCase(getCharacterByIdUseCaseImpl: GetCharacterByIdUseCaseImpl) : GetCharacterByIdUseCase
}