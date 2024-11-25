package com.herbarium.data.network.models

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class SupabasePlant(
    val id: String,
    val name: String,
    val name_latin: String?,
    val description: String?,
    val location: String?,
    val location_string: String?,
    val image_url: String?,
    val last_modified: Instant?,
)
