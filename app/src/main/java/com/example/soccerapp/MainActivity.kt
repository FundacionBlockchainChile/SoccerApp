package com.example.soccerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.soccerapp.ui.navigation.NavGraph
import com.example.soccerapp.ui.theme.SoccerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoccerAppTheme {
                // Usamos el Scaffold para proporcionar una consistencia visual y estructural en toda la app
                Scaffold { innerPadding ->
                    AppContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    // Incorporamos el NavGraph aquí para manejar la navegación entre pantallas
    NavGraph()
}
