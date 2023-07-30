package com.example.rickandmorty.domain

class GetCharacterUseCase(
    private val characterClient: CharacterClient
) {
    suspend fun getCharacterUseCase(characterId: String): DetailedCharacter? {
        return characterClient.getCharacter(characterId)
    }
}