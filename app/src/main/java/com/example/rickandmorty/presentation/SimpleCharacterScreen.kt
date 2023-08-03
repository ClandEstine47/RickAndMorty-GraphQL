package com.example.rickandmorty.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.domain.Results
import com.example.rickandmorty.ui.theme.Color4
import com.example.rickandmorty.ui.theme.Color5

@Composable
fun SimpleCharacterScreen(
    navController: NavController,
    state: CharacterViewModel.CharactersState,
    onSelectCharacter: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()
        .background(Color5)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.characters!!.results) { character ->
                    CharacterItem(
                        character = character,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectCharacter(character.id!!)
                                navController.navigate(
                                    route = CharacterScreens.DetailedCharacterScreen.name
                                )
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: Results,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(context = context)
                .data(character.image)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = character.name!!, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Gender: ${character.gender}")
            Text(text = "Status: ${character.status}")
            Text(text = "Species: ${character.species}")
            Text(text = "Type: ${if (character.type.isNullOrEmpty()) "Normal" else character.type}")
        }
    }
}
