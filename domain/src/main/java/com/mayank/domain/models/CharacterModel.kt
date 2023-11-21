package com.mayank.domain.models

data class CharacterModel(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val characterLocation: CharacterLocationModel,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
