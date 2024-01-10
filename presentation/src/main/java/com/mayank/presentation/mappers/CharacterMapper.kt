package com.mayank.presentation.mappers

import com.mayank.domain.models.CharacterModel
import com.mayank.presentation.models.CharacterItem
import javax.inject.Inject

class CharacterMapper @Inject constructor(
    private val characterLocationMapper: CharacterLocationMapper
) {
    fun mapFromModel(model: CharacterModel): CharacterItem {
        return with(model) {
            CharacterItem(
                gender = gender,
                id = id,
                image = image,
                characterLocation = characterLocationMapper.mapFromModel(characterLocation),
                name = name,
                species = species,
                status = status,
            )
        }
    }
}
