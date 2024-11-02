package com.herbarium.model

import android.location.Location
import android.media.Image

class PlantManager {
    private val plants: MutableList<Plant> = mutableListOf()

    fun getPlants(): List<Plant> = plants

    fun getPlantByName(name: String): Plant? = plants.firstOrNull { it.name == name }

    fun getPlantByNameLatin(nameLatin: String): Plant? = plants.firstOrNull { it.nameLatin == nameLatin }

    fun deletePlant(plant: Plant) = plants.remove(plant)

    fun addPlant(
        name: String,
        nameLatin: String?,
        description: String?,
        location: Location?,
        locationString: String?,
        image: Image
    ) {
        val newPlant = PlantBuilder()
            .setName(name)
            .setNameLatin(nameLatin)
            .setDescription(description)
            .setLocation(location)
            .setLocationString(locationString)
            .setImage(image)
            .build()

        plants.add(newPlant)
    }

    fun updatePlant(
        plant: Plant,
        updatedName: String?,
        updatedNameLatin: String?,
        updatedDescription: String?,
        updatedLocation: Location?,
        updatedLocationString: String?,
        updatedImage: Image
    ) {
        val updatedPlant = PlantBuilder()
        .setName(updatedName ?: plant.name)
        .setNameLatin(updatedNameLatin?: plant.nameLatin)
        .setDescription(updatedDescription?: plant.description)
        .setLocation(updatedLocation?: plant.location)
        .setLocationString(updatedLocationString?: plant.locationString)
        .setImage(updatedImage?: plant.image)
        .build()

        plants[plants.indexOf(plant)] = updatedPlant
    }

}