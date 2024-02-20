package com.mayank.presentation.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppComponent::class])
interface SubComponent {
    fun myDependency() : MyDependency
}