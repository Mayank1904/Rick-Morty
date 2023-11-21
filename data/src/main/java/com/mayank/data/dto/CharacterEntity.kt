package com.mayank.data.dto

data class CharacterEntity(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val characterLocation: CharacterLocationEntity,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
