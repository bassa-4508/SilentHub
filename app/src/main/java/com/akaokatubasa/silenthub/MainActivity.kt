package com.akaokatubasa.silenthub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.akaokatubasa.silenthub.ui.navigation.SilentHubBottomBar
import com.akaokatubasa.silenthub.ui.navigation.SilentHubNavHost
import com.akaokatubasa.silenthub.ui.theme.SilentHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SilentHubApp()
        }
    }
}

@Composable
private fun SilentHubApp() {
    SilentHubTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { SilentHubBottomBar(navController = navController) }
        ) { innerPadding ->
            SilentHubNavHost(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}
