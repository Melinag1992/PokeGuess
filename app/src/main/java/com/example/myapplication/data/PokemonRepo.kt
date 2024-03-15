package com.example.myapplication.data

import com.example.myapplication.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepo {
    suspend fun getPokemons():Flow<Results<List<Pokemon>>>
}