package com.herbarium.data.model

class GeoLocationBuilder {
    private var latitude: Double? = null
    private var longitude: Double? = null
    private var address: String? = null

    fun setLatitude(latitude: Double?) = apply { this.latitude = latitude }
    fun setLongitude(longitude: Double?) = apply { this.longitude = longitude }
    fun setAddress(address: String?) = apply { this.address = address }

    fun build(): GeoLocation {
        return GeoLocation(latitude, longitude, address)
    }
}