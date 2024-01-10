package com.mayank.presentation.models

data class CharacterItem(
    val gender: String,
    val id: Int,
    val image: String,
    val characterLocation: CharacterLocation,
    val name: String,
    val species: String,
    val status: String,
)
