package com.example.countercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.countercompose.ui.theme.CounterComposeTheme

enum class Screen {
    COUNTER,
    DATA_DIRI
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterComposeTheme {
                var currentScreen by rememberSaveable { mutableStateOf(Screen.COUNTER) }

                BackHandler(enabled = currentScreen == Screen.DATA_DIRI) {
                    currentScreen = Screen.COUNTER
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when (currentScreen) {
                        Screen.COUNTER -> {
                            CounterApp(
                                onNavigateToDataDiri = {
                                    currentScreen = Screen.DATA_DIRI
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        Screen.DATA_DIRI -> {
                            DataDiriScreen(
                                onBack = {
                                    currentScreen = Screen.COUNTER
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
