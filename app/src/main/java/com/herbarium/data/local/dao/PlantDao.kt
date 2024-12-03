package com.herbarium.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.herbarium.data.local.entities.PlantEntity

@Dao
interface PlantDao {

    // Insert a plant (return ID of the plant)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: PlantEntity): String

    // Insert multiple plants
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<PlantEntity>)

    @Update
    suspend fun update(plant: PlantEntity)

    @Delete
    suspend fun delete(plant: PlantEntity)

    //Delete all plants DON'T USE THIS lmao for testing only
    @Query("DELETE FROM plants")
    suspend fun deleteAll()

    // Get all plants as LiveData List (for UI related things)
    @Query("SELECT * FROM plants")
    fun getAllPlantsAsLiveData(): LiveData<List<PlantEntity>>

    // Get all plants as List (for non-UI related things)
    @Query("SELECT * FROM plants")
    fun getAllPlants(): List<PlantEntity>

    // Get plant by id
    @Query("SELECT * FROM plants WHERE id = :id LIMIT 1")
    suspend fun getPlantById(id: String): PlantEntity?


}