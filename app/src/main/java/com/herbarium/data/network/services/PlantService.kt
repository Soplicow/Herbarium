package com.herbarium.data.network.services

import com.herbarium.data.network.models.PlantSB
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.datetime.Clock
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
class PlantService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    private val postgrest = supabaseClient.postgrest
    private val storage = supabaseClient.storage

    suspend fun getAllPlants(): List<PlantSB> {
        return postgrest["plants"]
            .select()
            .decodeList<PlantSB>()
    }

    suspend fun insertPlant(plant: PlantSB): PlantSB {
        return postgrest["plants"]
           .insert(plant)
           .decodeSingle()
    }

    suspend fun deletePlant(plantId: String): Boolean {
        return try {
            val response = supabaseClient.from("plants")
                .delete {
                    filter {
                        eq("id", plantId)
                    }
                }

            true
        } catch (e: Exception) {
            e.printStackTrace() // Log the error for debugging
            false
        }
    }


    suspend fun uploadPlantImage(imagePath: String, imageData: ByteArray): String {
        val fileName = imagePath.substringAfterLast("/")
        storage["TODO"].upload(fileName, imageData)
        return "TODO"
    }

    suspend fun updatePlant(plant: PlantSB): Boolean {
        return try {
            supabaseClient.from("plants").update(
                {
                    set("name", plant.name)
                    set("nameLatin", plant.nameLatin)
                    set("description", plant.description)
                    set("location", plant.location)
                    set("imageUrl", plant.imageUrl)
                    set("lastModified", Clock.System.now())
                }
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}