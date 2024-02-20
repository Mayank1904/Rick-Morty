package com.mayank.presentation.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {
    fun myDependency() : MyDependency
}