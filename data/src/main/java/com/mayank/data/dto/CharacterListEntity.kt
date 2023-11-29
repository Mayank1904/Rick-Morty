package com.mayank.data.dto

import com.squareup.moshi.Json

data class CharacterListEntity(
//    val info: InfoEntity,
    @Json(name = "results")
    val characters: List<CharacterEntity>
)