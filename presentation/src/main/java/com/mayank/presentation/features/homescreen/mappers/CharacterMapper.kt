package com.mayank.presentation.features.homescreen.mappers

import com.mayank.domain.models.CharacterModel
import com.mayank.presentation.features.homescreen.models.CharacterItem
import javax.inject.Inject

class CharacterMapper @Inject constructor(
    private val characterLocationMapper: CharacterLocationMapper
)  {
     fun mapFromModel(model: CharacterModel): CharacterItem {
        return with(model) {
            CharacterItem(
                created = created,
                gender = gender,
                id = id,
                image = image,
                characterLocation = characterLocationMapper.mapFromModel(characterLocation),
                name = name,
                species = species,
                status = status,
                type = type,
                url = url,
                )
        }
    }
}
