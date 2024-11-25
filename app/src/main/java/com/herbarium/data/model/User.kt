package com.herbarium.data.model

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: ContactsContract.CommonDataKinds.Email,
    val passwordHash: Int,
    val salt: String
)