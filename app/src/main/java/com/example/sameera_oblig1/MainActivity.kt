package com.example.sameera_oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sameera_oblig1.ui.theme.Sameera_oblig1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sameera_oblig1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SkjermNaviasjon()
                }
            }
        }
    }
}

@Composable
fun SkjermNaviasjon() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Palindrome") {
        composable("Palindrome") { PalindromeScreen(modifier = Modifier.fillMaxSize(),onNavigateToUnitConverter = { navController.navigate("UnitConverter") }) }
        composable("UnitConverter") { UnitConverterScreen(modifier = Modifier.fillMaxSize(),onNavigateToQuiz = { navController.navigate("Quiz") }) }
        composable("Quiz") { QuizScreen(modifier = Modifier.fillMaxSize(),onNavigateToQuiz = { navController.navigate("Quiz") }) }
        /*...*/
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Sameera_oblig1Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SkjermNaviasjon()
        }
    }
}