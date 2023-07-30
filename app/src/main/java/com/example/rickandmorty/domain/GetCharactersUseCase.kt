package com.example.rickandmorty.domain

class GetCharactersUseCase(
    private val characterClient: CharacterClient
) {
    suspend fun getCharactersUseCase(page: Int): SimpleCharacter? {
        return characterClient.getCharacters(page)
    }
}