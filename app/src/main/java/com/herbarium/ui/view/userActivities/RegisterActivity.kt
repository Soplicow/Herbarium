package com.herbarium.ui.view.userActivities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.herbarium.ui.theme.HerbariumTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HerbariumTheme {
                RegisterScreen(
                    onRegister = { email, password ->
                        performRegistration(email, password)
                    }
                )
            }
        }
    }

    private fun performRegistration(email: String, password: String) {
        val salt = "salt"
        val passwordHash = (password+salt).hashCode()
    }
    // TODO: Implement salt and stuff using UserViewModel
}
