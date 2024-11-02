package com.herbarium.model

import android.location.Location
import android.media.Image

class Plant private constructor(
    private var name: String,
    private var nameLatin: String?,
    private var description: String?,
    private var location: Location?,
    private var locationString: String?,
    private var image: Image
) {
    val getName: String
        get() = name

    val getNameLatin: String?
        get() = nameLatin

    val getDescription: String?
        get() = description

    val getLocation: Location?
        get() = location

    val getLocationString: String?
        get() = locationString

    val getImage: Image
        get() = image

    class Builder {
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
        fun setImage(image: Image) = apply { this.image = image }

        fun build() = Plant(
            name = name!!,
            nameLatin = nameLatin,
            description = description,
            location = location,
            locationString = locationString,
            image = image!!
        )
    }

    companion object {
        fun create(builder: Builder) = builder.build()
    }
}