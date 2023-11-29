package com.mayank.data.api

import com.mayank.data.dto.CharacterEntity
import com.mayank.data.dto.CharacterListEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getCharacters(@Query("page") pageNumber: Int = 1): CharacterListEntity
    @GET("character/")
    suspend fun getCharacter(@Query("id") id: Int): CharacterEntity
}
