package com.example.myapplication.data.model

data class Pokemons(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Pokemon>
)