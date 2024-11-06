package com.herbarium.data.local.model

import android.location.Location
import android.media.Image

class PlantBuilder {
    private var name: String? = null
    private var nameLatin: String? = null
    private var description: String? = null
    private var location: Location? = null
    private var locationString: String? = null
    private var image: Image? = null

    fun setName(name: String) = apply { this.name = name }
    fun setNameLatin(nameLatin: String?) = apply { this.nameLatin = nameLatin }
    fun setDescription(description: String?) = apply { this.description = description }
    fun setLocation(location: Location?) = apply { this.location = location }
    fun setLocationString(locationString: String?) = apply { this.locationString = locationString }
    fun setImage(image: Image?) = apply { this.image = image }

    fun build(): Plant {
        val name = this.name ?: throw IllegalArgumentException("Name is required")
        val image = this.image ?: throw IllegalArgumentException("Image is required")
        return Plant(0, name, nameLatin, description, location, locationString, image)
    }
}