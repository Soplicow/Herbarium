package com.herbarium.data.model

import java.util.UUID

data class Plant(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val nameLatin: String?,
    val description: String?,
    val location: GeoLocation?,
    val imageUrl: String?,
)