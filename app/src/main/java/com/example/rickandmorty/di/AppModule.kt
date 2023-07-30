package com.example.rickandmorty.di

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.data.ApolloCharacterClient
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.GetCharacterUseCase
import com.example.rickandmorty.domain.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterClient(apolloClient: ApolloClient): CharacterClient {
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(characterClient: CharacterClient): GetCharactersUseCase {
        return GetCharactersUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetCharacterUseCase(characterClient: CharacterClient): GetCharacterUseCase {
        return GetCharacterUseCase(characterClient)
    }
}