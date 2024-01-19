package com.mayank.presentation.mappers

import com.mayank.domain.models.CharacterLocationModel
import com.mayank.presentation.models.CharacterLocation
import javax.inject.Inject

class CharacterLocationMapper @Inject constructor() {
    fun map(model: CharacterLocationModel): CharacterLocation {
        return with(model) {
            CharacterLocation(name = name)
        }
    }
}
