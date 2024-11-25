package com.herbarium.data.local.entities

import android.location.Location
import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_table")
data class PlantEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val nameLatin: String?,
    val description: String?,
    val location: Location?,
    val locationString: String?,
    val imageUrl: String?,
    val lastModified: Long? = null,
    val isSynced: Boolean
)
// TODO: Should those fields be public??? It would make things easier ig