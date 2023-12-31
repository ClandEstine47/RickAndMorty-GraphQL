package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.presentation.CharacterScreens
import com.example.rickandmorty.presentation.CharacterViewModel
import com.example.rickandmorty.presentation.DetailedCharacterScreen
import com.example.rickandmorty.presentation.SimpleCharacterScreen
import com.example.rickandmorty.ui.theme.RickandMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<CharacterViewModel>()
                val state by viewModel.state.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = CharacterScreens.SimpleCharacterScreen.name
                ) {
                    composable(CharacterScreens.SimpleCharacterScreen.name) {
                        SimpleCharacterScreen(
                            navController,
                            state,
                            onSelectCharacter = viewModel::onSelectCharacter
                        )
                    }

                    composable(CharacterScreens.DetailedCharacterScreen.name) {
                        DetailedCharacterScreen(
                            navController,
                            state,
                            onBackPressed = viewModel::onBackPressed
                        )
                    }
                }
            }
        }
    }
}