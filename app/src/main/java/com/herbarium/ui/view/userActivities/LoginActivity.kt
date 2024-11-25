package com.herbarium.ui.view.userActivities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.herbarium.ui.theme.HerbariumTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HerbariumTheme {
                LoginScreen(
                    onLogin = { email, password ->
                        performLogin(email, password)
                    }
                )
            }
        }
    }

    private fun performLogin(email: String, password: String) {
        val salt = "salt" // TODO: Implement salt and stuff using UserViewModel
        val passwordHash = (password+salt).hashCode()
    }
    // This is inside the LoginActivity class to isolate the login from the rest of the app
    // Same thing should be done with registration
}
