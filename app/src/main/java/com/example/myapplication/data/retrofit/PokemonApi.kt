package com.example.myapplication.data.retrofit

import com.example.myapplication.data.model.Pokemons
import retrofit2.http.GET

interface PokemonApi {


    @GET("pokemon/")
    suspend fun getPokemon() : Pokemons

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}