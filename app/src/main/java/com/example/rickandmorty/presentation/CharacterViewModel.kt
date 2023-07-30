package com.example.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.DetailedCharacter
import com.example.rickandmorty.domain.GetCharacterUseCase
import com.example.rickandmorty.domain.GetCharactersUseCase
import com.example.rickandmorty.domain.SimpleCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            )
            }
            _state.update { it.copy(
                characters = getCharactersUseCase.execute(1),
                character = null,
                isLoading = false
            ) }
        }
    }

    data class CharactersState(
        val characters: SimpleCharacter? = null,
        val character: DetailedCharacter? = null,
        val isLoading: Boolean = false
    )
}