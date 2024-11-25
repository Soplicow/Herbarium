package com.herbarium.data.model

import android.location.Location
import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Plant(
    val id: Long,
    val name: String,
    val nameLatin: String?,
    val description: String?,
    val location: GeoLocation?,
    val imageUrl: String?,
)
// TODO: Should those fields be public??? It would make things easier ig