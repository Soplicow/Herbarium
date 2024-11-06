package com.herbarium.data.local.model

import android.location.Location
import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "plant_table")
data class Plant(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val nameLatin: String?,
    val description: String?,
    val location: Location?,
    val locationString: String?,
    val image: Image
)
// TODO: Should those fields be public??? It would make things easier ig