package com.mayank.domain.di

import com.mayank.domain.usecases.CharacterListUseCase
import com.mayank.domain.usecases.CharacterListUseCaseImpl
import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.domain.usecases.GetCharacterByIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindCharacterListUseCase(characterListUseCaseImpl: CharacterListUseCaseImpl) : CharacterListUseCase

    @Binds
    abstract fun bindGetCharacterByIdUseCase(getCharacterByIdUseCaseImpl: GetCharacterByIdUseCaseImpl) : GetCharacterByIdUseCase
}