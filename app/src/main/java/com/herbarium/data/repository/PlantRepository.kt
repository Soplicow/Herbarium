package com.herbarium.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.herbarium.data.model.Plant
import com.herbarium.data.local.dao.PlantDao
import com.herbarium.data.local.entities.PlantEntity
import com.herbarium.data.model.PlantBuilder
import com.herbarium.data.network.models.PlantSB
import com.herbarium.data.network.services.PlantService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import javax.inject.Inject

class PlantRepository @Inject constructor (
    private val plantDao: PlantDao,
    private val plantService: PlantService
) {
    fun getPlants(): LiveData<List<Plant>> {
        val roomPlantsLiveData = plantDao.getAllPlantsAsLiveData()
        return roomPlantsLiveData.map { roomPlants
            -> roomPlants.mapNotNull { plantEntityToDomain(it) }
        }
    }

    suspend fun synchronize() = withContext(Dispatchers.IO) {
        // Fetch plants from both databases as Lists
        val supabasePlants = plantService.getAllPlants()
        val roomPlants = plantDao.getAllPlants()

        // Update supabase with local data
        roomPlants.forEach { roomPlant ->
            val supabasePlant = supabasePlants.firstOrNull { it.id == roomPlant.id }
            if (roomPlant.isDeleted) {
                if (supabasePlant != null) {
                    plantService.deletePlant(
                        supabasePlant.id
                    )
                    plantDao.update(roomPlant.copy(isSynced = true))
                }
                return@forEach
            }

            if (supabasePlant == null) {
                plantService.insertPlant(
                    plantEntityToPlantSB(roomPlant)
                )
                plantDao.update(roomPlant.copy(isSynced = true))
                return@forEach
            }

            if (!roomPlant.isSynced) {
                plantService.updatePlant(
                    plantEntityToPlantSB(roomPlant)
                )
                plantDao.update(roomPlant.copy(isSynced = true))
                return@forEach
            }
        }
    }

    suspend fun insert(newPlant: Plant) {
        val plant = plantDomainToEntity(newPlant)
        if (plant != null) {
            plantDao.insert(plant)
        }
    }

    suspend fun markForDeletion(deletedPlant: Plant) {
        val plant = plantDao.getPlantById(deletedPlant.id)
        if (plant != null) {
            plantDao.update(plant.copy(isDeleted = true))
        }
    }

    suspend fun getPlantById(id: String): Plant? {
        val plant = plantDao.getPlantById(id)
        return plantEntityToDomain(plant)
    }

    // Convert Room to domain
    private fun plantEntityToDomain(plant: PlantEntity?): Plant? {
        return if (plant != null) {
            PlantBuilder()
                .setId(plant.id)
                .setName(plant.name)
                .setNameLatin(plant.nameLatin)
                .setDescription(plant.description)
                .setLocation(plant.location)
                .setImageUrl(plant.imageUrl)
                .build()
        } else
            null
    }

    private fun plantDomainToEntity(plant: Plant?): PlantEntity? {
        return if (plant != null) {
            PlantEntity(
                id = plant.id,
                name = plant.name,
                nameLatin = plant.nameLatin,
                description = plant.description,
                location = plant.location,
                imageUrl = plant.imageUrl,
                lastModified = Clock.System.now(),
                isSynced = false,  // Assume it's not synced until we get the response from Supabase
                isDeleted = false
            )
        } else
            null
    }

    private fun plantEntityToPlantSB(plant: PlantEntity): PlantSB {
        return PlantSB(
            id = plant.id,
            name = plant.name,
            nameLatin = plant.nameLatin,
            description = plant.description,
            location = plant.location,
            imageUrl = plant.imageUrl,
            lastModified = plant.lastModified,
            isDeleted = plant.isDeleted
        )
    }


}