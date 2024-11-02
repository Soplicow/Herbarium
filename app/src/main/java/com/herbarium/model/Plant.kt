package com.herbarium.model

import android.location.Location
import android.media.Image

data class Plant(val name: String,
                     val nameLatin: String?,
                     val description: String?,
                     val location: Location?,
                     val locationString: String?,
                     val image: Image
)
// TODO: Should those fields be public??? It would make things easier ig