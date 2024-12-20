package com.herbarium.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.herbarium.data.model.GeoLocation
import kotlinx.datetime.Instant

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val nameLatin: String?,
    val description: String?,
    val location: GeoLocation?,
    val imageUrl: String?,
    val lastModified: Instant?,
    val isSynced: Boolean,
    val isDeleted: Boolean
)
// TODO: Should those fields be public??? It would make things easier ig