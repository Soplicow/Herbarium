package com.herbarium.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.herbarium.data.model.Plant

@Dao
interface PlantDao {

    // Insert a plant (return ID of the plant)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Plant): Long

    // Insert multiple plants
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Plant>)

    @Update
    suspend fun update(plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)

    //Delete all plants DON'T USE THIS lmao for testing only
    @Query("DELETE FROM plant_table")
    suspend fun deleteAll()

    // Get all plants
    @Query("SELECT * FROM plant_table")
    fun getAllPlants(): LiveData<List<Plant>>

    // Get plant by id
    @Query("SELECT * FROM plant_table WHERE id = :id LIMIT 1")
    suspend fun getPlantById(id: Int): Plant?


}