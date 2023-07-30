package com.example.rickandmorty.domain

class GetCharactersUseCase(
    private val characterClient: CharacterClient
) {
    suspend fun execute(page: Int): SimpleCharacter? {
        return characterClient.getCharacters(page)
    }
}