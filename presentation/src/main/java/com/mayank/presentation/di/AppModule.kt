package com.mayank.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideMyDependency() : MyDependency = MyDependency()
}

@Module
@InstallIn(SingletonComponent::class)
object SubModule {
    @Singleton
    @Provides
    fun provideMyDependency(subComponent: SubComponent) : MyDependency = subComponent.provideMyDependency()
}