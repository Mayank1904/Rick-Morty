package com.mayank.presentation

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.mayank.presentation.features.homescreen.CharacterList
import com.mayank.presentation.models.CharacterItem
import com.mayank.presentation.models.CharacterLocation
import org.junit.Rule
import org.junit.Test

class CharacterListSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun launchComposable() {
        paparazzi.snapshot {
            CharacterList(
                listOfCharacters,
            ) {}
        }
    }

    private companion object {
        val listOfCharacters = arrayListOf(
            CharacterItem(
                gender = "Male",
                id = 1,
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                name = "Rick Sanchez",
                species = "Human",
                status = "Alive",
                characterLocation = CharacterLocation(
                    name = "Citadel of Rick",
                )
            ),
            CharacterItem(
                gender = "Male",
                id = 2,
                image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                name = "Morty Smith",
                species = "Human",
                status = "Alive",
                characterLocation = CharacterLocation(
                    name = "Citadel of Ricks",
                )
            ),
            CharacterItem(
                gender = "unknown",
                id = 36,
                image = "https://rickandmortyapi.com/api/character/avatar/36.jpeg",
                name = "Beta-Seven",
                species = "Alien",
                status = "Alive",
                characterLocation = CharacterLocation(
                    name = "Earth",
                )
            )
        )
    }

}