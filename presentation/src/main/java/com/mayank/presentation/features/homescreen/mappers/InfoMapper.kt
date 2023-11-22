package com.mayank.presentation.features.homescreen.mappers

import com.mayank.domain.models.InfoModel
import com.mayank.presentation.features.homescreen.models.Info
import javax.inject.Inject

class InfoMapper @Inject constructor() {
     fun mapFromModel(model: InfoModel): Info {
        return with(model) {
            Info(
                count = count,
                next = next,
                pages = pages,
                prev = prev
            )
        }
    }
}
