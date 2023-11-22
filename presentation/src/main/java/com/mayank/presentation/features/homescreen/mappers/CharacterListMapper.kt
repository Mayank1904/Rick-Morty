package com.mayank.presentation.features.homescreen.mappers

import com.mayank.domain.models.CharacterListModel
import com.mayank.presentation.features.homescreen.models.CharacterList
import javax.inject.Inject

class CharacterListMapper @Inject constructor(
    private val characterMapper: CharacterMapper,
    private val infoMapper: InfoMapper
)  {
    fun mapFromModel(model: CharacterListModel): CharacterList {
        return with(model) {
            CharacterList(info = infoMapper.mapFromModel(info),
                characters = characters.map { characterMapper.mapFromModel(it) }
            )
        }
    }
}