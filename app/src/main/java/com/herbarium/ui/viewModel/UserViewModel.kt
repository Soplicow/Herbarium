package com.herbarium.ui.viewModel

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herbarium.data.model.User
import com.herbarium.data.repository.UserRepository
import kotlinx.coroutines.launch
import android.provider.ContactsContract.CommonDataKinds.Email

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    // Holds the current user data
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    // Holds the login status
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    init {
        viewModelScope.launch {
            userRepository.currentUser.also { _user.value = it }
            _isLoggedIn.value = _user.value != null
        }
    }

    fun login(email: Email, password: String) {
        viewModelScope.launch {
            val result = userRepository.login(email, password)
            if (result!=null) {
                _isLoggedIn.value = true
            } else {
                _isLoggedIn.value = false
            }
        }
    }
}