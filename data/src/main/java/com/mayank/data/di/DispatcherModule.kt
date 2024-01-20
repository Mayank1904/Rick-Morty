package com.mayank.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object DispatcherModule {
    @Provides
    @ViewModelScoped
    fun provideDispatcher() = Dispatchers.IO
}
