package com.example.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.DetailedCharacter
import com.example.rickandmorty.domain.GetCharacterUseCase
import com.example.rickandmorty.domain.GetCharactersUseCase
import com.example.rickandmorty.domain.SimpleCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
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

    fun onSelectCharacter(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(
                character = getCharacterUseCase.execute(id)
            ) }
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _state.update { it.copy(
                character = null
            ) }
        }
    }
}