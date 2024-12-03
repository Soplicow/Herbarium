package com.herbarium.data.model

import java.util.UUID

class PlantBuilder {
    private var id: String = UUID.randomUUID().toString()
    private var name: String? = null
    private var nameLatin: String? = null
    private var description: String? = null
    private var location: GeoLocation? = null
    private var imageUrl: String? = null

    fun setId(id: String) = apply { this.id = id } // Should be used only when mapping from database to local
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