package com.herbarium.data.repository

import androidx.lifecycle.LiveData
import com.herbarium.data.local.model.Plant
import com.herbarium.data.local.db.PlantDao

class PlantRepository(private val plantDao: PlantDao) {

    val allPlants: LiveData<List<Plant>> = plantDao.getAllPlants()

    suspend fun insert(plant: Plant) {
        plantDao.insert(plant)
    }

    suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    suspend fun delete(plant: Plant) {
        plantDao.delete(plant)
    }

    suspend fun getPlantById(id: Int): Plant? {
        return plantDao.getPlantById(id)
    }
}