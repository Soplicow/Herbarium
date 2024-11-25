package com.herbarium.data.model

import android.location.Location

data class GeoLocation(
    val latitude: Double?,
    val longitude: Double?,
    val address: String? // Derived or stored
) {
    // Computed property for location string
    val locationString: String?
        get() = address ?: "$latitude, $longitude"

    val location: Location?
        get() = if (latitude != null && longitude != null) Location("").apply {
            latitude = latitude
            longitude = longitude
        } else null
}
