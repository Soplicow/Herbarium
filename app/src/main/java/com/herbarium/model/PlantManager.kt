package com.herbarium.model

import android.location.Location
import android.media.Image

class PlantManager {
    private val plants: MutableList<Plant> = mutableListOf()

    fun addPlant(
        name: String,
        nameLatin: String?,
        description: String?,
        location: Location?,
        locationString: String?,
        image: Image
    ) {
        val newPlant = Plant.create(Plant.Builder()
            .setName(name)
            .setNameLatin(nameLatin)
            .setDescription(description)
            .setLocation(location)
            .setLocationString(locationString)
            .setImage(image)
        )

        plants.add(newPlant)
    }

    fun getPlants(): List<Plant> = plants

    fun getPlantByName(name: String): Plant? = plants.firstOrNull { it.getName == name }

    fun getPlantByNameLatin(nameLatin: String): Plant? = plants.firstOrNull { it.getNameLatin == nameLatin }

    fun deletePlant(plant: Plant) = plants.remove(plant)

    fun updatePlant(
        plant: Plant,
        updatedName: String?,
        updatedNameLatin: String?,
        updatedDescription: String?,
        updatedLocation: Location?,
        updatedLocationString: String?,
        updatedImage: Image
    ) {
        val updatedPlant = Plant.create(Plant.Builder()
        .setName(updatedName ?: plant.getName)
        .setNameLatin(updatedNameLatin?: plant.getNameLatin)
        .setDescription(updatedDescription?: plant.getDescription)
        .setLocation(updatedLocation?: plant.getLocation)
        .setLocationString(updatedLocationString?: plant.getLocationString)
        .setImage(updatedImage?: plant.getImage)
        )

        plants[plants.indexOf(plant)] = updatedPlant
    }
}