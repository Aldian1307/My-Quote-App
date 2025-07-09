package com.example.aplikasiquote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.FirebaseApp
import com.example.aplikasiquote.Quote
import com.example.aplikasiquote.EditQuoteScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            MyQuotesApp()
        }
    }
}

@Composable
fun MyQuotesApp(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "display"){
        composable("display"){
            DisplayQuoteScreen(navController)
        }
        composable("add") {
            AddQuoteScreen(navController)
        }
        composable("edit") {
            EditQuoteScreen(navController)

        }
    }
}