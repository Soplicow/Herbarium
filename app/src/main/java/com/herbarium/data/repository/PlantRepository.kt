package com.herbarium.data.repository

import androidx.lifecycle.LiveData
import com.herbarium.data.model.Plant
import com.herbarium.data.local.dao.PlantDao
import com.herbarium.data.network.services.PlantService
import com.herbarium.data.network.services.SupabaseModule
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlantRepository @Inject constructor (
    private val plantDao: PlantDao,
    private val plantService: PlantService
) {
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

    suspend fun synchronize() = withContext(Dispatchers.IO) {

    }
}