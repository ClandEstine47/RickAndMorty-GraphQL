package com.example.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import com.example.CharactersQuery
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.DetailedCharacter
import com.example.rickandmorty.domain.SimpleCharacter

class ApolloCharacterClient(
    private val apolloClient: ApolloClient
): CharacterClient {
    override suspend fun getCharacters(page: Int): SimpleCharacter? {
        return apolloClient
            .query(CharactersQuery(page))
            .execute()
            .data
            ?.characters
            ?.toSimpleCharacter()
    }

    override suspend fun getCharacter(characterID: String): DetailedCharacter? {
        TODO("return DetailedCharacter")
    }
}