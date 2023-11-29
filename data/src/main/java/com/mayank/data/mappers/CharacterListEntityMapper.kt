package com.mayank.data.mappers

import com.mayank.data.dto.CharacterListEntity
import com.mayank.domain.models.CharacterListModel
import javax.inject.Inject

class CharacterListEntityMapper @Inject constructor(
    private val characterEntityMapper: CharacterEntityMapper,
) {
    fun mapFromEntity(entity: CharacterListEntity): CharacterListModel {
        return with(entity) {
            CharacterListModel(characters = characters.map {
                characterEntityMapper.mapFromEntity(it)
            }
            )
        }
    }
}
