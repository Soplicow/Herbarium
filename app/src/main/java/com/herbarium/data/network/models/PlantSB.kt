package com.herbarium.data.network.models

import com.herbarium.data.model.GeoLocation
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class PlantSB(
    val id: String,
    val name: String,
    val nameLatin: String?,
    val description: String?,
    val location: GeoLocation?,
    val imageUrl: String?,
    val lastModified: Instant?,
    val isDeleted: Boolean
)
