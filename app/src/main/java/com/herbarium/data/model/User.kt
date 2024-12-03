package com.herbarium.data.model

import android.provider.ContactsContract.CommonDataKinds.Email
import java.util.UUID

data class User (
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: Email,
    val passwordHash: String,
    val salt: String
)