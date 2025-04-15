package com.benyaamin.customernote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.benyaamin.customernote.ui.navigation.AppContent
import com.benyaamin.customernote.ui.theme.CustomerNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.Companion.light(0, 0))
        setContent {
            CustomerNoteTheme {
                Surface {
                    val navController = rememberNavController()

                    BackHandler {
                        val navigateUp = navController.navigateUp()
                        if (!navigateUp) finish()
                    }

                    AppContent(navController, this)
                }
            }
        }
    }
}