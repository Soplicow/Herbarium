package com.herbarium.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val email: String,
    val passwordHash: String,
    val salt: String,
    val lastModified: Instant?,
    val isSynced: Boolean = false,
    val lastLoggedIn: Instant?,
)