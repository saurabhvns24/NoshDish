package com.example.dish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dish.ui.theme.DishTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DishTheme {
                val viewModel = viewModel<NoshViewModel>()
                val dishes by viewModel.dishes.collectAsState()
                MainScreen(dishes = dishes)
            }
        }
    }
}