package com.mayank.presentation.di

import javax.inject.Inject

class MyDependency @Inject constructor(){
    fun doSomething() {
        println("Doing Something")
    }
}