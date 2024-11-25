package com.herbarium.data.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import com.herbarium.data.local.dao.UserDao
import com.herbarium.data.model.User

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    // Interactions with the user
    var currentUser: User? = null

    suspend fun login(email: Email, password: String): User? {
        val user = userDao.getUserByEmail(email)

//        if (user!=null) {
//            currentUser = user
//            return user
//        }


        return null
    }

    suspend fun logout() {
        currentUser = null
    }

    // Interactions with database
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }

    suspend fun getUserByEmail(email: Email): User? {
        return userDao.getUserByEmail(email)
    }

}