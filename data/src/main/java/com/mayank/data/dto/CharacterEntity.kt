package com.mayank.data.dto

import com.squareup.moshi.Json

data class CharacterEntity(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    @Json(name = "location")
    val characterLocation: CharacterLocationEntity,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
