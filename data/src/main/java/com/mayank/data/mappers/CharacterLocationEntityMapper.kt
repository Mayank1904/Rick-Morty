package com.mayank.data.mappers

import com.mayank.data.dto.CharacterLocationEntity
import com.mayank.domain.models.CharacterLocationModel
import javax.inject.Inject

class CharacterLocationEntityMapper @Inject constructor() {
    fun map(entity: CharacterLocationEntity): CharacterLocationModel {
        return with(entity) {
            CharacterLocationModel(name = name, url = url)
        }
    }
}
