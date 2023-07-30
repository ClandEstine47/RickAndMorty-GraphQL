package com.example.rickandmorty.domain

interface CharacterClient {
    suspend fun getCharacters(page: Int): List<SimpleCharacter>
    suspend fun getCharacter(characterID: String): DetailedCharacter?
}