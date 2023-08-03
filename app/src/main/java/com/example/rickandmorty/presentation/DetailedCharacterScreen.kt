package com.example.rickandmorty.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DetailedCharacterScreen(
    navController: NavController,
    state: CharacterViewModel.CharactersState
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(context = context)
                    .data(state.character?.image)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Gray)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Name: " + state.character?.name, fontSize = 20.sp)
        Text(text = "Gender: " + state.character?.gender, fontSize = 20.sp)
        Text(text = "Species: " + state.character?.species, fontSize = 20.sp)
        Text(text = "Status: " + state.character?.status, fontSize = 20.sp)
        Text(text = "Type: " + if (state.character?.type.isNullOrEmpty()) "None" else state.character?.type, fontSize = 20.sp)
    }
}