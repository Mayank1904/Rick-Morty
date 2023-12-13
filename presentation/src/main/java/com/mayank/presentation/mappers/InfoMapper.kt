package com.mayank.presentation.mappers

import com.mayank.domain.models.InfoModel
import com.mayank.presentation.models.Info
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
