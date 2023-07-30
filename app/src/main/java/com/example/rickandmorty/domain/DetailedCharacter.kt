package com.example.rickandmorty.domain

data class DetailedCharacter(
    val created: String?,
    val gender: String?,
    val id: String?,
    val image: String?,
    val location: Location,
    val name: String?,
    val origin: Origin,
    val species: String?,
    val status: String?,
    val type: String?
)
