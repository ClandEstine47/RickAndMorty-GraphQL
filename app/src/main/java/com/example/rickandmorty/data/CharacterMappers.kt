package com.example.rickandmorty.data

import com.example.CharactersQuery
import com.example.rickandmorty.domain.Info
import com.example.rickandmorty.domain.Results
import com.example.rickandmorty.domain.SimpleCharacter

fun CharactersQuery.Characters.toSimpleCharacter(): SimpleCharacter {
    return SimpleCharacter(
        info = Info(
            count = info?.count ?: 0,
            next = info?.next ?:0,
            pages = info?.pages ?: 0,
            prev = info?.prev ?: 0
        ),
        results = results!!.map { result ->
            Results(
                name = result?.name!!,
                id = result.id!!,
                gender = result.gender!!,
                image = result.image!!,
                species = result.species!!,
                status = result.status!!,
                type = result.type!!
            )
        }
    )
}