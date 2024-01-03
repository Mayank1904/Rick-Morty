package com.mayank.data.mappers

import com.mayank.data.dto.CharacterEntity
import com.mayank.domain.models.CharacterModel
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor(
    private val characterLocationEntityMapper: CharacterLocationEntityMapper
)  {
     fun mapFromEntity(entity: CharacterEntity): CharacterModel {
        return with(entity) {
            CharacterModel(
                created = created,
                gender = gender,
                id = id,
                image = image,
                characterLocation = characterLocationEntityMapper.mapFromEntity(location),
                name = name,
                species = species,
                status = status,
                type = type,
                url = url,
                )
        }
    }
}
