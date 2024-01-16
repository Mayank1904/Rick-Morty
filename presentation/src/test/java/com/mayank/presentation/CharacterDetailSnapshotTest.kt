package com.mayank.presentation

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.mayank.presentation.features.detailscreen.DetailScreen
import com.mayank.presentation.models.CharacterItem
import com.mayank.presentation.models.CharacterLocation
import org.junit.Rule
import org.junit.Test

class CharacterDetailSnapshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun launchComposable() {
        paparazzi.snapshot {
            DetailScreen(
                item
            )
        }
    }

    private companion object {
        val item = CharacterItem(
            gender = "Male",
            id = 1,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            name = "Rick Sanchez",
            species = "Human",
            status = "Alive",
            characterLocation = CharacterLocation(
                name = "Citadel of Rick",
            )
        )
    }
}