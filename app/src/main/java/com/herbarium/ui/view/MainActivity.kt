package com.herbarium.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import com.herbarium.ui.theme.HerbariumTheme

val supabase = createSupabaseClient(
    supabaseUrl = "https://zwpanainvfxrarkhyokh.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inp3cGFuYWludmZ4cmFya2h5b2toIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzI0NDMzMzYsImV4cCI6MjA0ODAxOTMzNn0.ezzRcuUjVJnRLmzil5N928teAr3RMqrO1aD3dDOkUwM"
) {
    install(Postgrest)
    //install other modules
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HerbariumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                Scaffold(modifier = Modifier.fillMaxHeight()) { innerPadding ->
                    Text(
                        text = "Welcome to Herbarium!",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HerbariumTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun HerbariumThemePreview() {
    HerbariumTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface, // You can change this to any color for contrast
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Display primary color
                ColorBox(
                    color = MaterialTheme.colorScheme.primary,
                    label = "Primary"
                )
                // Display secondary color
                ColorBox(
                    color = MaterialTheme.colorScheme.secondary,
                    label = "Secondary"
                )
                // Display background color
                ColorBox(
                    color = MaterialTheme.colorScheme.background,
                    label = "Background"
                )
                // Display surface color
                ColorBox(
                    color = MaterialTheme.colorScheme.surface,
                    label = "Surface"
                )
                // Display error color
                ColorBox(
                    color = MaterialTheme.colorScheme.error,
                    label = "Error"
                )
            }
        }
    }
}

// Helper composable to display a colored box with a label
@Composable
fun ColorBox(color: Color, label: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = label, color = MaterialTheme.colorScheme.onBackground)
    }
}
