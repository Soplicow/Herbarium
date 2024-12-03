package com.herbarium.data.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class SupabaseUser (
    val id: String,
    val name: String,
    val email: String,
    val passwordHash: String,
    val salt: String,
    val createdAt: Instant,
    val lastModifiedAt: Instant
)