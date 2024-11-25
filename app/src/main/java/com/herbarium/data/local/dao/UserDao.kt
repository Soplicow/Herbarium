package com.herbarium.data.local.dao

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.herbarium.data.model.User

@Dao
interface UserDao {

    // Insert a user (return ID of the user)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    // Insert multiple users
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    //Delete all users DON'T USE THIS lmao for testing only
    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    // Get user by email
    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: Email): User?

    // Get all users
    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<User>>

    // Get user by id
    @Query("SELECT * FROM user_table WHERE id = :id LIMIT 1")
    suspend fun getUserById(id: Int): User?
}