package com.mayank.data.mappers

import com.mayank.data.dto.InfoEntity
import com.mayank.domain.models.InfoModel
import javax.inject.Inject

class InfoEntityMapper @Inject constructor() {
     fun mapFromEntity(entity: InfoEntity): InfoModel {
        return with(entity) {
            InfoModel(
                count = count,
                next = next,
                pages = pages,
                prev = prev
            )
        }
    }
}
