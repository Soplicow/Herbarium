package com.herbarium.data.network.services

import com.herbarium.data.network.models.SupabasePlant
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
class PlantService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    private val postgrest = supabaseClient.postgrest
    private val storage = supabaseClient.storage

    suspend fun getAllPlants(): List<SupabasePlant> {
        return postgrest["plants"]
            .select()
            .decodeList<SupabasePlant>()
    }

    suspend fun insertPlant(plant: SupabasePlant): SupabasePlant {
        return postgrest["plants"]
           .insert(plant)
           .decodeSingle()
    }

    suspend fun uploadPlantImage(imagePath: String, imageData: ByteArray): String {
        val fileName = imagePath.substringAfterLast("/")
        storage["TODO"].upload(fileName, imageData)
        return "TODO"
    }
}