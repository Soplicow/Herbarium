package com.herbarium.data.model

import android.location.Location
import android.media.Image

class PlantBuilder {
    private var id: Long = 0
    private var name: String? = null
    private var nameLatin: String? = null
    private var description: String? = null
    private var location: GeoLocation? = null
    private var imageUrl: String? = null

    fun setId(id: Long) = apply { this.id = id }
    fun setName(name: String) = apply { this.name = name }
    fun setNameLatin(nameLatin: String?) = apply { this.nameLatin = nameLatin }
    fun setDescription(description: String?) = apply { this.description = description }
    fun setLocation(location: GeoLocation?) = apply { this.location = location }
    fun setImageUrl(imageUrl: String?) = apply { this.imageUrl = imageUrl }

    fun build(): Plant {
        val name = this.name ?: throw IllegalArgumentException("Name is required")
        return Plant(id, name, nameLatin, description, location, imageUrl)
    }
}